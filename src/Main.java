import java.util.HashMap;

public class Main {
    private static void printUser(HashMap<String, Object> user) {
        if (user.containsKey("id")) {
            System.out.println("Fetched user with id `" + user.get("id").toString() + "`");
        }
        if (user.containsKey("name")) {
            System.out.println("name: `" + user.get("name").toString() + "`");
        }
        if (user.containsKey("email")) {
            System.out.println("email: `" + user.get("email").toString() + "`");
        }
        if (user.containsKey("tier")) {
            System.out.println("tier: " + user.get("tier").toString());
        }
        System.out.println("======");
    }

    public static void main(String[] args) {
        DatabaseClient dbClient = new DatabaseClient();
        ApiService apiService = new ApiService(dbClient);

        // Get a User with id user_1
        System.out.println(apiService.getUser("user_1"));

        // Insert a User with id user_10
        HashMap<String, Object> user1 = new HashMap<String, Object>();
        user1.put("id", "user_10");
        apiService.insertUser(user1);
        System.out.println("User entered with id: user_10");

        // Insert a User with id user_15
        HashMap<String, Object> user2 = new HashMap<String, Object>();
        user2.put("id", "user_15");
        user2.put("name", "Thomas Hilpold");
        user2.put("email", "tom.hilpold@zeynrobotics.com");
        user2.put("tier", 0);
        apiService.insertUser(user2);
        System.out.println("User entered with id: user_15");

        // Delete a User with id user_10
        apiService.remove("user_10");
        System.out.println("User removed with id: user_10");

        // Get a User with id user_10
        try {
            HashMap<String, Object> user3 = apiService.getUser("user_10");
            System.out.println("Something went wrong. The user with id `user_10` should not exist");
        } catch (Exception e) {
            System.out.println("Did not find user with id: `user_10`");
        }

        // Get a User with id user_15
        try {
            HashMap<String, Object> user4 = apiService.getUser("user_15");
            printUser(user4);
        } catch (Exception e) {
            System.out.println("Did not find user with id: `user_15`");
        }

        // Insert a User with id user_10
        HashMap<String, Object> user5 = new HashMap<String, Object>();
        user5.put("id", "user_10");
        user5.put("name", "Mark Smith");
        user5.put("email", "mark.smith@zeynrobotics.com");
        user5.put("tier", 1);
        apiService.insertUser(user5);
        System.out.println("User entered with id: user_10");

        // Get a User with id user_10
        try {
            HashMap<String, Object> user6 = apiService.getUser("user_10");
            printUser(user6);
        } catch (Exception e) {
            System.out.println("Did not find user with id: `user_10`");
        }
    }
}