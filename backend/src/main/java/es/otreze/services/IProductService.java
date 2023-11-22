package es.otreze.services;

import es.otreze.dtos.SaveProductDTO;
import es.otreze.persistence.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IProductService {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById(UUID productId);

    Product createOne(SaveProductDTO saveProduct);

    Product UpdateOneById(UUID productId, SaveProductDTO saveProduct);

    Product disableById(UUID id);

}
