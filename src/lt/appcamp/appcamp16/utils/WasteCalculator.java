package lt.appcamp.appcamp16.utils;

import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.model.Waste;

public class WasteCalculator {
    public static Waste calculate(Item item) {
        Waste result = new Waste();
        
        double randomCoeff = (item.id % 10) / 10.0;
        
        Double r = item.category.getAvgPrice().doubleValue() * randomCoeff;
        result.waterLiters = r.intValue();

        r = item.category.getAvgWeight() * randomCoeff;

        result.co2Kg = r.intValue();
        
        return result;
    }
    
}
