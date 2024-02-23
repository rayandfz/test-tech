package fr.rayandfz.back.service;

import fr.rayandfz.back.model.Product;

import java.util.Collection;

/**
 * Interface for product-related operations.
 *
 * This interface defines the contract for services handling products,
 * including creation, retrieval, updating, and deletion of product records.
 */
public interface IProductService {

    /**
     * Creates a new product record.
     *
     * @param product The product to be created.
     * @return The created product, including its generated ID.
     */
    Product createProduct(final Product product);

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    Collection<Product> getAllProducts();

    /**
     * Retrieves a single product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The requested product.
     * @throws RuntimeException If no product is found with the given ID.
     */
    Product getProductById(final Long id);

    /**
     * Updates an existing product's details.
     *
     * @param id The ID of the product to update.
     * @param productDetails A product object containing the updated details.
     * @return The updated product.
     * @throws RuntimeException If no product is found with the given ID.
     */
    Product updateProduct(final Long id, final Product productDetails);

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @throws RuntimeException If no product is found with the given ID.
     */
    void deleteProduct(final Long id);
}
