package lt.appcamp.appcamp16.utils;

import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.model.Waste;

public class WasteCalculator {
    public static Waste calculate(Item item) {
        Waste result = new Waste();
        
        double randomCoeff = (item.id % 10 + 1) / 10.0;

        Double avgPrice = 30.0;
        Double avgWeight = 0.3;
        
        if (item.category != null) {
            avgPrice = item.category.getAvgPrice();
            avgWeight = item.category.getAvgWeight();
        }
        result.waterLiters = avgPrice.doubleValue() * 34.5 * randomCoeff;

        result.co2Kg = avgWeight * 20 * randomCoeff;
        
        return result;
    }
    
}
