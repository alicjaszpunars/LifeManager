package pl.coderslab.lifemanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.lifemanager.config.UnsafeRestTemplate;

import java.util.Map;


@Service
public class StockApiService {
   private final RestTemplate restTemplate;

   public StockApiService() throws  Exception{
       this.restTemplate=UnsafeRestTemplate.create();
   }
    private final String apiKey = "06YMA1I0K75I1AFW";

    public double getPrice(String ticker) {

        String url = "https://www.alphavantage.co/query"
                + "?function=GLOBAL_QUOTE"
                + "&symbol=" + ticker
                + "&apikey=" + apiKey;

        Map<?, ?> response = restTemplate.getForObject(url, Map.class);

        System.out.println("STOCK API RESPONSE: " + response);


        if (response == null || !response.containsKey("Global Quote")) {
            throw new RuntimeException("Stock API error");
        }

        Map<?, ?> quote = (Map<?, ?>) response.get("Global Quote");
        Object priceObj = quote.get("05. price");

        if (priceObj == null) {
            throw new RuntimeException("Stock price missing");
        }

        return Double.parseDouble(priceObj.toString());
    }
}


