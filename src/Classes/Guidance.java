package Classes;

import java.util.ArrayList;

@SuppressWarnings("Duplicates, unused")
class Guidance {
    private int id;
    private int rating;
    private String name;
    private String info;
    private String location;
    private ArrayList<String> photos;
    private ArrayList<Contact> contacts;
    private ArrayList<Comment> comments;
    private ArrayList<String> amenties;
    private ArrayList<Integer> ratings;

    Guidance() {
        rating = 0;
        photos = new ArrayList<>();
        contacts = new ArrayList<>();
        comments = new ArrayList<>();
        amenties = new ArrayList<>();
        ratings = new ArrayList<>();
    }

    public String ratingsToString() {
        if (ratings.isEmpty())
            return "";

        String string = "";
        for (Integer rating1 : ratings) string += rating1 + "/";
        return string.substring(0, string.length() - 1);
    }

    public void setRatings(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '/' || i == string.length() - 1) {
                int last = i == string.length() - 1 ? i + 1 : i;
                ratings.add(Integer.parseInt(string.substring(index, last)));
                index = i + 1;
            }
        calculateRating();
    }

    public void addRating(int rating) {
        ratings.add(rating);
        calculateRating();
    }

    private void calculateRating() {
        if (ratings.isEmpty())
            return;

        int counter = 0;
        for (Integer rating1 : ratings) counter += rating1;
        rating = counter / ratings.size();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public String ammenityToString() {
        if (amenties.size() == 0)
            return "";

        String string = "";
        for (String amenity : amenties)
            string += amenity + "■";
        return string.substring(0, string.length() - 1);
    }

    public String commentsToString() {
        if (comments.size() == 0)
            return "";

        String string = "";

        for (Comment comment : comments)
            string += comment.toString() + "■";
        return string.substring(0, string.length() - 1);
    }

    public String contactsToString() {
        if (contacts.size() == 0)
            return "";

        String string = "";
        for (Contact contact : contacts)
            string += contact.toString() + "■";
        return string.substring(0, string.length() - 1);
    }

    public String getImageLinks() {
        if (photos.size() == 0)
            return "";
        String string = "";
        for (String image : photos)
            string += image + "■";
        return string.substring(0, string.length() - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImages(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                int last = i == string.length() - 1 ? i + 1 : i;
                photos.add(string.substring(index, last));
                index = i + 1;
            }
    }

    public void setContacts(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                int last = i == string.length() - 1 ? i + 1 : i;
                contacts.add(new Contact(string.substring(index, last)));
                index = i + 1;
            }
    }

    public void setComments(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                int last = i == string.length() - 1 ? i + 1 : i;
                comments.add(new Comment(string.substring(index, last)));
                index = i + 1;
            }
    }

    public void setAmenities(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == '■' || i == string.length() - 1) {
                int last = i == string.length() - 1 ? i + 1 : i;
                amenties.add(string.substring(index, last));
                index = i + 1;
            }
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<String> getAmenties() {
        return amenties;
    }

    public void setAmenities(ArrayList<String> amenties) {
        this.amenties = amenties;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }
}
