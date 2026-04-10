package pl.coderslab.lifemanager.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CurrencyApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Double getRate(String currencyCode) {

        String url = "https://api.nbp.pl/api/exchangerates/rates/A/"
                + currencyCode
                + "/?format=json";
        Map<?, ?> response = restTemplate.getForObject(url, Map.class);

        List<?> rates = (List<?>) response.get("rates");
        Map<?, ?> rate = (Map<?, ?>) rates.get(0);

        return Double.parseDouble(rate.get("mid").toString());


    }
}
