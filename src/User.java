import java.util.Optional;

public class User {
    private String id;
    private Optional<String> name;
    private Optional<String> email;
    private Optional<Integer> tier;

    // Constructor
    public User(String id, Optional<String> name, Optional<String> email, Optional<Integer> tier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tier = tier;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<Integer> getTier() {
        return tier;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = Optional.of(name);
    }

    public void setEmail(String email) {
        this.email = Optional.of(email);
    }

    public void setTier(Integer tier) {
        this.tier = Optional.of(tier);
    }
}
