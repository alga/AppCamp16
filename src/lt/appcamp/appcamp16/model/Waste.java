package lt.appcamp.appcamp16.model;

public class Waste {
    public Double waterLiters;
    public Double co2Kg;
    
    @Override
    public String toString() {
        return "Water: " + Math.round(waterLiters) + " ltr, CO2: " + Math.round(co2Kg * 10) / 10.0 + " kg";
        
    }
}
