import java.util.Optional;
import java.util.HashMap;

public class DatabaseClient {
    private HashMap<String, User> data;

    public DatabaseClient() {
        this.data = new HashMap<String, User>();

        User userOne = new User(
                "user_1",
                Optional.of("John Smith"),
                Optional.of("john_smith@zeynrobotics.com"),
                Optional.of(0));
        insertIntoDb(userOne);

        User userTwo = new User(
                "user_2",
                Optional.of("Nick Fernandez"),
                Optional.of("nick_fernandez@zeynrobotics.com"),
                Optional.of(1));
        insertIntoDb(userTwo);

        User userThree = new User(
                "user_3",
                Optional.of("Finn O'Brien"),
                Optional.of("finn_obrien@zeynrobotics.com"),
                Optional.of(1));
        insertIntoDb(userThree);
    }

    public void insertIntoDb(User entity) throws NullPointerException {
        this.data.put(entity.getId(), entity);
    }

    public User getFromDb(String key) throws NullPointerException {
        if (!this.data.containsKey(key)) {
            throw new NullPointerException("Could not find `" + key + "` in DB.");
        }
        return this.data.get(key);
    }

    public void remove(String key) throws NullPointerException {
        if (!this.data.containsKey(key)) {
            throw new NullPointerException("Could not find `" + key + "` to remove from DB.");
        }
        this.data.remove(key);
    }
}
