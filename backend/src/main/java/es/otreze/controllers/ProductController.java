package es.otreze.controllers;


import es.otreze.dtos.SaveProductDTO;
import es.otreze.persistence.entities.Product;
import es.otreze.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    /* Product with pagination */
    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {

        Page<Product> productsPage = productService.findAll(pageable);

        if (productsPage.hasContent()){
            return ResponseEntity.ok(productsPage);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable UUID productId) {

        Optional<Product> product = productService.findOneById(productId);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Validated SaveProductDTO saveProduct) {

        Product product = productService.createOne(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> UpdateOneById(@PathVariable UUID productId, @RequestBody @Validated SaveProductDTO saveProduct) {
        Product product = productService.UpdateOneById(productId, saveProduct);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    @PutMapping("/{productId}/disabled")
    public ResponseEntity<Product> disableById(@PathVariable UUID productId) {

        Product product = productService.disableById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }







}
