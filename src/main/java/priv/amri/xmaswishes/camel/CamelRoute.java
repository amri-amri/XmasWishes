package priv.amri.xmaswishes.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import priv.amri.xmaswishes.model.Wish;

import java.util.ArrayList;
import java.util.List;


public class CamelRoute extends RouteBuilder {

    String filepath;

    CamelRoute(String filepath) {
        this.filepath = filepath;
    }

    public void configure() {
        CsvDataFormat csv = new CsvDataFormat();
        csv.setDelimiter(";");
        csv.setSkipHeaderRecord("true");

        from("file:" + filepath + "?noop=true")
                .unmarshal(csv)
                .process(exchange -> {
                    List<List<String>> records = exchange.getIn().getBody(List.class);
                    List<Wish> wishes = new ArrayList<>(records.size());
                    for (List<String> record : records) {
                        String name = record.get(0);
                        String text = record.get(1);
                        Wish wish = new Wish(name, text);
                        wishes.add(wish);
                    }
                    exchange.getIn().setBody(wishes);
                })
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .marshal().json()
                .to("http://localhost:8080/persist");
    }

}