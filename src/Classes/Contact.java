package Classes;

@SuppressWarnings("unused")
public class Contact {
    private enum Type{
        Facebook, Site, PhoneNumber, Mail, Telegram, Other
    }
    private Type type;
    private String source;

    public Contact(){
    }

    public Contact(String source, Type type){
        this.source = source;
        this.type = type;
    }

    Contact(String string) {
        setType(Type.valueOf(string.substring(0, string.indexOf("□"))));
        setSource(string.substring(string.indexOf("□") + 1, string.length()));
    }

    private void setSource(String source) {
        this.source = source;
    }

    private void setType(Type type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public Type getType() {
        return type;
    }

    public String toString(){
        return type + "□" + source;
    }
}
