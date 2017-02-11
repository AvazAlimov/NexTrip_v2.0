package Classes;

public class ThingsToDo extends Guidance {
    private Date startDate;
    private Date endDate;
    private double price;
    private String rules;

    public ThingsToDo() {

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
