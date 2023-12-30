package es.otreze.services.implementations;

import es.otreze.dtos.SaveProductDTO;
import es.otreze.exceptions.ObjectNotFoundException;
import es.otreze.persistence.entities.Category;
import es.otreze.persistence.entities.Product;
import es.otreze.persistence.repositories.ProductRepository;
import es.otreze.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findOneById(UUID productId) {

    return productRepository.findById(productId);
    }

    @Override
    @Transactional
    public Product createOne(SaveProductDTO saveProduct) {
        Product product = new Product();
        product.setName(saveProduct.getName());
        product.setPrice(saveProduct.getPrice());
        product.setStatus(true);
        product.setStock(saveProduct.getStock());
        product.setHasStock(saveProduct.getHasStock());

        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product UpdateOneById(UUID productId, SaveProductDTO saveProduct) {
        Product productFromBD = productRepository.findById(productId).orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));
        productFromBD.setName(saveProduct.getName());
        productFromBD.setPrice(saveProduct.getPrice());
        productFromBD.setStock(saveProduct.getStock());
        productFromBD.setHasStock(saveProduct.getHasStock());

        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        productFromBD.setCategory(category);

        return productRepository.save(productFromBD);
    }

    @Override
    @Transactional
    public Product disableById(UUID productId) {
        Product productFromBD = productRepository.findById(productId).orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));
        productFromBD.setStatus(!productFromBD.getStatus());
        return productRepository.save(productFromBD);
    }
}
