package fr.rayandfz.back.controller;

import fr.rayandfz.back.model.Product;
import fr.rayandfz.back.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for handling requests related to Products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // Use constructor injection for better testability and invesion of control
    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    @PostMapping
    public Product createProduct(@RequestBody @Valid final Product product) {
        return productService.createProduct(product);
    }

    /**
     * Retrieve all products.
     *
     * @return a list of all product
     */
    @GetMapping
    public Collection<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Retrieve details for a single product by its id.
     *
     * @param id the id of the product to retrieve
     * @return the requested product
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable final Long id) {
        final Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    /**
     * Update details of a product
     *
     * @param id             the id of the product to update
     * @param productDetails the product details to update
     * @return the updated product
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable final Long id, @RequestBody @Valid final Product productDetails) {
        final Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Remove a product by its id.
     *
     * @param id the id of the product to remove
     * @return a response entity indicating the operation's status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
