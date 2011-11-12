package lt.appcamp.appcamp16.model;



public class Category {
    String title;
    Integer id;
    Integer view = null;
    /* average price in LTL */
    Double avgPrice = null;
    
    /* average weight in kg */
    Double avgWeight = null;
    

    public Category(String title, Integer id) {
        this.title = title;
        this.id = id;
    }
    
    public Category(String title, Integer id, Double avgPrice, Double avgWeight, Integer draw) {
        this.title = title;
        this.id = id;
        this.avgPrice = avgPrice;
        this.avgWeight = avgWeight;
        this.view = draw;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title; 
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Double getAvgPrice() {
        return avgPrice;
    }
    
    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getAvgWeight() {
        return avgWeight;
    }
    
    public void setAvgWeight(Double avgWeight) {
        this.avgWeight = avgWeight;
    }

    public Integer getView() {
        return this.view;
    }
    
    public void setView(Integer view) {
        this.view = view;
    }
}
