package lt.appcamp.appcamp16.model;

public class Waste {
    public Double waterLiters;
    public Double co2Kg;
    
    public String waterString() {
        return Math.round(waterLiters) + " l";
    }
    
    public String carbonString() {
        return Math.round(co2Kg * 10) / 10.0 + 10 + " kg";
    }
    
    @Override
    public String toString() {
        return "Water: " + this.waterString() + ", CO2: " + this.carbonString();
        
    }
}
