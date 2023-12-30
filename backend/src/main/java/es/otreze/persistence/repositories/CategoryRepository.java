package es.otreze.persistence.repositories;

import es.otreze.persistence.entities.Category;
import es.otreze.persistence.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<List<Category>> findByIsActiveTrue();
}
