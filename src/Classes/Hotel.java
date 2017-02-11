package Classes;

public class Hotel extends Guidance {
    private double startingPrice;
    private double endingPrice;

    public Hotel(String content) {
        System.out.println(content);
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getEndingPrice() {
        return endingPrice;
    }

    public void setEndingPrice(double endingPrice) {
        this.endingPrice = endingPrice;
    }
}
