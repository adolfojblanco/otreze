package es.otreze.controllers;

import es.otreze.persistence.entities.Category;
import es.otreze.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /* Get all categories paginate */
    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryService.findAllByOrderByIdAsc(pageable);
        if (categoryPage.hasContent()) {
            return ResponseEntity.ok(categoryPage);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Validated Category cat) {
        Category category = categoryService.create(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }


    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<UUID> disableById(@PathVariable UUID categoryId) {
        Category category = categoryService.disableById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryId);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateById(@PathVariable UUID categoryId, @RequestBody @Validated Category category){
        Category cat = categoryService.update(categoryId, category);
        return ResponseEntity.status(HttpStatus.OK).body(cat);
    }

}
