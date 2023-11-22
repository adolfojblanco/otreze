package es.otreze.services;

import es.otreze.persistence.entities.Category;
import es.otreze.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ICategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(UUID categoryID);

    Category create(Category category);

    Category update(UUID categoryId, Category category);

    Category disableById(UUID categoryId);

}
