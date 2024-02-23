package fr.rayandfz.back.service;

import fr.rayandfz.back.model.Product;
import fr.rayandfz.back.model.ProductCategory;
import fr.rayandfz.back.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ProductService
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @InjectMocks
    private ProductService productService;

    private Product product;

    /**
     * Common setup before each test.
     */
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setCategory(ProductCategory.CLOTHING);
        product.setRating(4.5);
    }

    /**
     * Test product creation.
     */
    @Test
    public void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product created = productService.createProduct(product);

        assertNotNull(created);
        assertEquals(product.getName(), created.getName());
        assertEquals(product.getDescription(), created.getDescription());
        assertEquals(product.getPrice(), created.getPrice());
        assertEquals(product.getQuantity(), created.getQuantity());
        assertEquals(product.getCategory(), created.getCategory());
        assertEquals(product.getRating(), created.getRating());
    }

    /**
     * Tests retrieving all products.
     */
    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        Collection<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
    }

    /**
     * Test retrieving all products when the list is empty.
     */
    @Test
    public void testGetAllProducts_EmptyList() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        Collection<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

    /**
     * Test retrieving a product by its ID.
     */
    @Test
    public void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.getProductById(1L);

        assertNotNull(found);
        assertEquals(product.getId(), found.getId());
    }

    /**
     * Test error handling when retrieving a product by an ID that does not exist.
     */
    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(1L);
        });

        String expectedMessage = "Product not found with id 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests updating a product
     */
    @Test
    public void testUpdateProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Name");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(200.0);
        updatedProduct.setQuantity(20);
        updatedProduct.setCategory(ProductCategory.FITNESS);
        updatedProduct.setRating(5.0);

        Product result = productService.updateProduct(1L, updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getDescription(), result.getDescription());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        assertEquals(updatedProduct.getQuantity(), result.getQuantity());
        assertEquals(updatedProduct.getCategory(), result.getCategory());
        assertEquals(updatedProduct.getRating(), result.getRating());

        verify(productRepository, times(1)).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();

        assertEquals(updatedProduct.getName(), capturedProduct.getName());
        assertEquals(updatedProduct.getDescription(), capturedProduct.getDescription());
        assertEquals(updatedProduct.getPrice(), capturedProduct.getPrice());
        assertEquals(updatedProduct.getQuantity(), capturedProduct.getQuantity());
        assertEquals(updatedProduct.getCategory(), capturedProduct.getCategory());
        assertEquals(updatedProduct.getRating(), capturedProduct.getRating());
    }

    /**
     * Tests deleting a product.
     */
    @Test
    public void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(eq(product));
    }
}
