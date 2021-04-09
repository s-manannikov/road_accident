package accident.repository;

import org.springframework.data.repository.CrudRepository;
import accident.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}