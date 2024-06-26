package exercise.controller.users;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private List<Post> posts = Data.getPosts();

//    Реализуйте дополнительные обработчики маршрутов для Post:
//
//    GET /api/users/{id}/posts — список всех постов, написанных пользователем с таким же userId, как id в маршруте
//    POST /api/users/{id}/posts – создание нового поста, привязанного к пользователю по id. Код должен возвращать статус 201, тело запроса должно содержать slug, title и body. Обратите внимание, что userId берется из маршрута

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> show(@PathVariable int id) {

        var result = posts.stream().filter(p -> p.getUserId() == id).toList();

        return result;
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable int id, @RequestBody Post data) {

        data.setUserId(id);
        posts.add(data);
        return data;
    }


}


// END
