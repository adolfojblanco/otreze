package es.otreze.services.implementations;

import es.otreze.exceptions.ObjectNotFoundException;
import es.otreze.persistence.entities.Category;
import es.otreze.persistence.repositories.CategoryRepository;
import es.otreze.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(UUID categoryID) {
        return categoryRepository.findById(categoryID);
    }

    @Override
    @Transactional
    public Category create(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setActive(true);
        return categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public Category update(UUID categoryId, Category category) {
        Category updateCategory = this.findById(categoryId).orElseThrow((() -> new ObjectNotFoundException("Category not found")));
        updateCategory.setName(category.getName());
        return categoryRepository.save(updateCategory);
    }

    @Override
    @Transactional
    public Category disableById(UUID categoryId) {
        Category updateCategory = this.findById(categoryId).orElseThrow((() -> new ObjectNotFoundException("Category not found")));
        updateCategory.setActive(!updateCategory.isActive());
        return categoryRepository.save(updateCategory);
    }
}
