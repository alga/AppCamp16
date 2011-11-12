package lt.appcamp.appcamp16.model;

public class Waste {
    public int waterLiters;
    public int co2Kg;
    
    @Override
    public String toString() {
        return "water: " + waterLiters + " ltr, CO2: " + co2Kg + " kg";
        
    }
}
