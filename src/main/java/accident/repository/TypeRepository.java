package accident.repository;

import accident.model.AccidentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<AccidentType, Integer> {
}
