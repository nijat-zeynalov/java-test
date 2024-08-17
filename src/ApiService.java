import java.util.Optional;
import java.util.HashMap;

public class ApiService {
    DatabaseClient dbClient;

    public ApiService(DatabaseClient dbClient) {
        this.dbClient = dbClient;
    }

    public HashMap<String, Object> getUser(String userId) throws IllegalArgumentException {
        try {
            User user = this.dbClient.getFromDb(userId);
            Optional<String> name = user.getName();
            Optional<String> email = user.getEmail();
            Optional<Integer> tier = user.getTier();

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", user.getId());
            name.ifPresent(s -> map.put("name", s));
            email.ifPresent(s -> map.put("email", s));
            tier.ifPresent(i -> map.put("tier", i));
            return map;
        } catch (Exception e) {
            throw new IllegalArgumentException("User does not exist for id: " + userId);
        }
    }

    public void insertUser(HashMap<String, Object> user) throws IllegalArgumentException {
        if (!user.containsKey("id")) {
            throw new IllegalArgumentException("Missing User id in the payload");
        }
        String id = user.get("id").toString();
        Optional<String> name = Optional.ofNullable(user.get("name")).map(Object::toString);
        Optional<String> email = Optional.ofNullable(user.get("email")).map(Object::toString);
        Optional<Integer> tier = Optional.ofNullable(user.get("tier")).filter(obj -> obj instanceof Integer).map(obj -> (Integer) obj);
        User newUser = new User(id, name, email, tier);
        this.dbClient.insertIntoDb(newUser);
    }

    public void remove(String key) throws NullPointerException {
        try {
            this.dbClient.remove(key);
        } catch (Exception e) {
            throw new IllegalArgumentException("User can not be deleted since it does not exist for id: " + key);
        }
    }
}
