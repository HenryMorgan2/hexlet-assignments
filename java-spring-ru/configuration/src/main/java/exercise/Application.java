package exercise;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    private UserProperties userProperties;

    @GetMapping("/admins")
    public ResponseEntity<List<String>> admins(){
        List<String> adminsEmail = userProperties.getAdmins().stream().distinct().collect(Collectors.toList());

        List<String> nameAdmins = new ArrayList<>();

        for (String elementEmail: adminsEmail) {

            for (User user: users) {

                String adminEmail = user.getEmail();
                if (elementEmail.equals(adminEmail)){
                    nameAdmins.add(user.getName());
                }
            }
        }

        Collections.sort(nameAdmins);


        return ResponseEntity.ok(nameAdmins);
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
