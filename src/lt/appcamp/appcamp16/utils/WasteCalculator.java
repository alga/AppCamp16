package lt.appcamp.appcamp16.utils;

import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.model.Waste;

public class WasteCalculator {
    public static Waste calculate(Item item) {
        Waste result = new Waste();
        
        double randomCoeff = (item.id % 10 + 1) / 10.0;

        Integer avgPrice = 30;
        Integer avgWeight = 300;
        
        if (item.category != null) {
            avgPrice = item.category.getAvgPrice();
            avgWeight = item.category.getAvgWeight();
        }
        Double r = avgPrice.doubleValue() * randomCoeff;
        result.waterLiters = r.intValue();

        r = avgWeight / 1000.0 * 20 * randomCoeff;

        result.co2Kg = r.intValue();
        
        return result;
    }
    
}
