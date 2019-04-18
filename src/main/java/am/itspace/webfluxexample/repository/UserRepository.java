package am.itspace.webfluxexample.repository;

import am.itspace.webfluxexample.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
