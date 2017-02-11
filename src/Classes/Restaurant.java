package Classes;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Restaurant extends Guidance {
    private enum Type {
        Cafe, Fastfood, Cuisine, SitDown
    }

    private ArrayList<Type> type;
    private Menu menu;
    private int numberOfSeats;

    public Restaurant() {
        type = new ArrayList<>();
        menu = new Menu();
        numberOfSeats = 0;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
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

    public void setMenu(String string) {
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

    public void setType(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '/' || i == string.length() - 1) {
                int last = i == string.length() - 1 ? i + 1 : i;
                type.add(Type.valueOf(string.substring(index, last)));
                index = i + 1;
            }
    }
}