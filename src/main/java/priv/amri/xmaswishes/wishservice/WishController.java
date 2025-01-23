package priv.amri.xmaswishes.wishservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@RestController
public class WishController {

    private final WebClient webClient;

    WishController(@Value("${dtbsurl}") String databaseURL) {
        ConnectionProvider provider = ConnectionProvider.builder("custom")
                .maxConnections(5000)  // Increase max connections
                .pendingAcquireMaxCount(20000)  // Increase pending queue size
                .pendingAcquireTimeout(Duration.ofSeconds(60))  // Increase acquire timeout
                .build();

        webClient = WebClient.builder()
                .baseUrl(databaseURL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
                .build();
    }

    int count = 0;

    @PostMapping("/wish")
    void wish(@RequestParam(name = "name") String name, @RequestParam(name = "text") String text) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", name);
        params.add("text", text);


        webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(params)
                .retrieve()
                .toBodilessEntity()
                .subscribe();

    }
}
