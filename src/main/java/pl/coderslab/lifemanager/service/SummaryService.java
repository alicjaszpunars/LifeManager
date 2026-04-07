package pl.coderslab.lifemanager.service;

import pl.coderslab.lifemanager.entity.*;

public class SummaryService {


    private String resolveType(Saving saving) {
        if (saving instanceof SavingStock) return "STOCK";
        if (saving instanceof SavingBond) return "BOND";
        if (saving instanceof SavingPPK) return "PPK";
        if (saving instanceof SavingDeposit) return "DEPO";
        if (saving instanceof  SavingCurrency) return "CURRENCY";
        return "OTHER";
    }

}
