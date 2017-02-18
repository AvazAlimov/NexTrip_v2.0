package Classes;

import java.util.ArrayList;

@SuppressWarnings("unused")
class Restaurant extends Guidance {
    private enum Type {
        Cafe, Fastfood, Cuisine, SitDown
    }

    private ArrayList<Type> type;
    private Menu menu;
    private int numberOfSeats;

    public Restaurant(String content) {
        type = new ArrayList<>();
        menu = new Menu();
        numberOfSeats = 0;

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

        setType(content.substring(indexes[9] + 1, indexes[10]));
        setMenu(content.substring(indexes[10] + 1, indexes[11]));
        setNumberOfSeats(Integer.parseInt(content.substring(indexes[11] + 1, content.length())));
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    private void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public ArrayList<Type> getType() {
        return type;
    }

    public void setType(ArrayList<Type> type) {
        this.type = type;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String menuToString() {
        return menu.toString();
    }

    private void setMenu(String string) {
        menu.setMenu(string);
    }

    public String typeToString() {
        if (type.isEmpty())
            return "";

        String string = "";

        for (Type type : this.type)
            string += type + "/";

        return string.substring(0, string.length() - 1);
    }

    private void setType(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '/' || i == string.length() - 1) {
                int last = i == string.length() - 1 ? i + 1 : i;
                type.add(Type.valueOf(string.substring(index, last)));
                index = i + 1;
            }
    }
}