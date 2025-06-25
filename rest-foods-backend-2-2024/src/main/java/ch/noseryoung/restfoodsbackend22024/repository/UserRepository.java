package ch.noseryoung.restfoodsbackend22024.repository;

import ch.noseryoung.restfoodsbackend22024.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Benutzer anhand der E-Mail finden
    Optional<User> findByEmail(String email);

    // Optional: Login-Funktion
    Optional<User> findByEmailAndPassword(String email, String password);
}
