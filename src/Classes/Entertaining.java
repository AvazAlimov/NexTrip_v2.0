package Classes;

public class Entertaining extends Guidance {
    private double price;
    private String rules;
    private int ageLimit;

    public Entertaining(){
        price = 0.0;
        rules = "";
        ageLimit = 0;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}
