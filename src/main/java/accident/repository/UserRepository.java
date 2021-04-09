package accident.repository;

import org.springframework.data.repository.CrudRepository;
import accident.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}