package priv.amri.xmaswishes.datacloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import priv.amri.xmaswishes.model.Wish;
import priv.amri.xmaswishes.model.WishRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class DatabaseLogic {

    @Value("${northpoleurl}")
    String northpoleurl;

    Logger logger = LoggerFactory.getLogger(DatabaseLogic.class);

    DatabaseLogic(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    WishRepository wishRepository;

    @Scheduled(cron = "0 0 4 * * ?")
    public String flushDatabase() {
        try {
            // Send data to North Pole
            RestTemplate restTemplate = new RestTemplate();

            List<Wish> wishes = wishRepository.findAll();
            int size = wishes.size();

            // Header für JSON-Format setzen
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // JSON-Request-Body erstellen
            HttpEntity<List<Wish>> request = new HttpEntity<>(wishes, headers);

            // POST-Anfrage senden
            String response = restTemplate.postForObject(northpoleurl, request, String.class);

            // Empty database
            wishRepository.deleteAll();

            logger.info("Successfully flushed {} wishes", size);
            return "database flushed";

        } catch (Exception e) {
            logger.error("Failed to flush wishes. Exception: {}", e.getMessage());
            return "flush of database failed";
        }
    }

    public String persistWish(Wish wish) {
        try {
            wishRepository.saveAndFlush(wish);
            return "wish persited";
        } catch (Exception e) {
            return "error";
        }
    }
}
