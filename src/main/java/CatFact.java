import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatFact {

    private final String id;
    private final String user;
    private final String text;

    public CatFact(@JsonProperty("_id") String id, @JsonProperty("user") String user, @JsonProperty("text") String text) {
        this.id = id;
        this.user = user;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "CatFact #" + id + "\n" +
                "Posted by user: " + user + "\n"
                + text + "\n";
    }
}