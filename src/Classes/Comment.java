package Classes;

@SuppressWarnings("unused")
public class Comment {
    private String comment;
    private Date writtenDate;
    private Guest guest;

    public Comment() {

    }

    Comment(String string) {
        int index = string.indexOf('□', 0);
        setComment(string.substring(0, index));
        int index2 = string.indexOf('□', index + 1);
        setWrittenDate(new Date(string.substring(index + 1, index2)));
        Guest guest = new Guest();
        guest.setUsername(string.substring(index2 + 1, string.length()));
        setGuest(guest);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setWrittenDate(Date writtenDate) {
        this.writtenDate = writtenDate;
    }

    public Date getWrittenDate() {
        return writtenDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return comment + "□" + writtenDate.toString() + "□" + guest.getUsername();
    }
}
