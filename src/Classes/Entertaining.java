package Classes;

public class Entertaining extends Guidance {
    private double price;
    private String rules;
    private int ageLimit;

    public Entertaining(String content) {
        price = 0.0;
        rules = "";
        ageLimit = 0;

        int[] indexes = new int[12];
        int index = 0;
        for (int i = 0; i < content.length(); i++)
            if (content.charAt(i) == '◎') {
                indexes[index] = i;
                index++;
            }

        setId(Integer.parseInt(content.substring(0, indexes[0])));
        setRating(Integer.parseInt(content.substring(indexes[0] + 1, indexes[1])));
        setName(content.substring(indexes[1] + 1, indexes[2]));
        setInfo(content.substring(indexes[2] + 1, indexes[3]));
        setLocation(content.substring(indexes[3] + 1, indexes[4]));
        setImages(content.substring(indexes[4] + 1, indexes[5]));
        setContacts(content.substring(indexes[5] + 1, indexes[6]));
        setComments(content.substring(indexes[6] + 1, indexes[7]));
        setAmenities(content.substring(indexes[7] + 1, indexes[8]));
        setRatings(content.substring(indexes[8] + 1, indexes[9]));

        setPrice(Double.parseDouble(content.substring(indexes[9] + 1, indexes[10])));
        setRules(content.substring(indexes[10] + 1, indexes[11]));
        setAgeLimit(Integer.parseInt(content.substring(indexes[11] + 1, content.length())));
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public String getRules() {
        return rules;
    }

    private void setRules(String rules) {
        this.rules = rules;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    private void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}
