package fr.rayandfz.back.service;

import fr.rayandfz.back.model.Product;
import fr.rayandfz.back.repository.IProductRepository;
import fr.rayandfz.back.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Service class for managing products.
 *
 * This class provides service-layer functionalities for handling
 * CRUD operations related to products. It uses the {@link IProductRepository}
 * for database interactions.
 */
@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    /**
     * Constructs a ProductService with the necessary repository.
     *
     * @param productRepository The repository used for product data operations.
     */
    @Autowired
    public ProductService(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product getProductById(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product updateProduct(final Long id, final Product productDetails) {
        Product existingProduct = getProductById(id);
        Utils.copyNonNullProperties(productDetails, existingProduct);
        return productRepository.save(existingProduct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProduct(final Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
