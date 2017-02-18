package Classes;

@SuppressWarnings("unused")
class Hotel extends Guidance {
    private double startingPrice;
    private double endingPrice;

    public Hotel(String content) {
        int[] indexes = new int[11];
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

        setStartingPrice(Double.parseDouble(content.substring(indexes[9] + 1, indexes[10])));
        setEndingPrice(Double.parseDouble(content.substring(indexes[10] + 1, content.length())));
    }

    @Override
    public String toString() {
        return super.toString() + startingPrice + "◎" + endingPrice;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    private void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getEndingPrice() {
        return endingPrice;
    }

    private void setEndingPrice(double endingPrice) {
        this.endingPrice = endingPrice;
    }
}
