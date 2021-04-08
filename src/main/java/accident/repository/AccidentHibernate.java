package accident.repository;

import accident.model.AccidentType;
import accident.model.Rule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import accident.model.Accident;

import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void create(Accident accident) {
        transaction(session -> session.merge(accident));
    }

    public List<Accident> getAccidents() {
        return transaction(session -> session.createQuery(
                "select distinct a from Accident a join fetch a.type join fetch a.rules", Accident.class
        ).list());
    }

    public Accident findAccidentById(int id) {
        return transaction(session -> session.createQuery("from Accident where id = :id", Accident.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    public List<AccidentType> getAccidentTypes() {
        return transaction(session -> session.createQuery("from AccidentType", AccidentType.class).list());
    }

    public List<Rule> getRules() {
        return transaction(session -> session.createQuery("from Rule", Rule.class).list());
    }

    private <T> T transaction(final Function<Session, T> command) {
        final Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (final Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }
    }
}