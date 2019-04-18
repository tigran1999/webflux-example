package am.itspace.webfluxexample.restController;

import am.itspace.webfluxexample.repository.UserRepository;
import am.itspace.webfluxexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by Tigran Mkrtchyan.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Create user mono.
     *
     * @param user the user
     * @return the mono
     */
    @PostMapping("/user")
    public Mono<User> createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    @GetMapping("/user/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable(value = "id") String userId) {
        return userRepository.findById(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Update user mono.
     *
     * @param userId the user id
     * @param user   the user
     * @return the mono
     */
    @PutMapping("/user/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable(value = "id") String userId,
                                                 @RequestBody User user) {
        return userRepository.findById(userId)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setSurname(user.getSurname());
                    return userRepository.save(existingUser);
                })
                .map(updateUser -> new ResponseEntity<>(updateUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete tweet mono.
     *
     * @param userId the user id
     * @return the mono
     */
    @DeleteMapping("/user/{id}")
    public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String userId) {

        return userRepository.findById(userId)
                .flatMap(existingUser ->
                        userRepository.delete(existingUser)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
