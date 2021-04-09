package accident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import accident.model.Accident;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Integer> {

    @NonNull
    @Override
    @Query("select distinct a from Accident a join fetch a.type join fetch a.rules")
    List<Accident> findAll();
}