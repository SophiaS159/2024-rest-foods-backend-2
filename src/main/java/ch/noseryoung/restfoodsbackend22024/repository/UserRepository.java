package ch.noseryoung.restfoodsbackend22024.repository;

import ch.noseryoung.restfoodsbackend22024.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    // Benutzer anhand der E-Mail finden
    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);
}
