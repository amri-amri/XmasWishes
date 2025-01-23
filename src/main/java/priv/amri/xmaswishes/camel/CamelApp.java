package priv.amri.xmaswishes.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelApp {

    final static String DEFAULT_FILEPATH = "src/main/resources";

    public static void main(String[] args) throws Exception {
        String filepath = DEFAULT_FILEPATH;
        if (args.length > 1) {
            filepath = args[0];
        }

        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new CamelRoute(filepath));

        context.start();
        Thread.sleep(5000);
        context.stop();
    }
}
