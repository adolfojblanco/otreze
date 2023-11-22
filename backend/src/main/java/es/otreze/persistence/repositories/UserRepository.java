package es.otreze.persistence.repositories;

import es.otreze.persistence.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    /** From spring security **/
    Optional<User> findOneByUsername(String username);
}
