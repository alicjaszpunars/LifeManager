package pl.coderslab.lifemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.entity.SavingCurrency;
import pl.coderslab.lifemanager.entity.SavingStock;
import pl.coderslab.lifemanager.repository.SavingsRepository;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketValueService {

    private final SavingsRepository savingsRepository;
    private final CurrencyApiService currencyApiService;
    private final SavingValueService savingValueService;
    private final StockApiService stockApiService;

    public void updateValuesManually() {
        LocalDate today = LocalDate.now();
        List<Saving> savings = savingsRepository.findAll();
        for (Saving saving : savings) {
            if (savingValueService.existForDate(saving, today)) {
                continue;
            }
            if (saving instanceof SavingCurrency currency) {
                double rate = currencyApiService.getRate(currency.getCurrencyCode());
                double value = currency.calculateValue(rate);
                value=Math.round(value*100.0)/100.0;
                savingValueService.saveMarketValue(saving, today, value);

            }
            if (saving instanceof SavingStock stock) {
                double stockPrice = stockApiService.getPrice(stock.getTicker());
                double currencyRate = currencyApiService.getRate(stock.getCurrencyCode());
                double value = stock.calculateValue(stockPrice, currencyRate);
                value=Math.round(value*100.0)/100.0;
                savingValueService.saveMarketValue(saving, today, value);

                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        }


    }

}

