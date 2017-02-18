package Classes;

@SuppressWarnings("unused")
class ThingsToDo extends Guidance {
    private Date startDate;
    private Date endDate;
    private double price;
    private String rules;


    public ThingsToDo(String content) {
        int[] indexes = new int[13];
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

        setStartDate(new Date(content.substring(indexes[9] + 1, indexes[10])));
        setEndDate(new Date(content.substring(indexes[10] + 1, indexes[11])));
        setPrice(Double.parseDouble(content.substring(indexes[11] + 1, indexes[12])));
        setRules(content.substring(indexes[12] + 1, content.length()));
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private void setStartDate(Date startDate) {
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

    private void setRules(String rules) {
        this.rules = rules;
    }
}
