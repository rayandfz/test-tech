package fr.rayandfz.back.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Represents a product entity.
 * <p>
 * This entity class is used to represent a product in the system. It is mapped to a database table 'products' using JPA annotations.
 * Each product has attributes like code, name, description, etc., which are validated for correctness using Jakarta Bean Validation annotations.
 * </p>
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product code is required")
    private String code;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be greater or equal to 0")
    private Integer quantity;

    @NotNull(message = "Inventory Status is required")
    private ProductInventoryStatus inventoryStatus;

    @NotNull(message = "Category is required")
    private ProductCategory category;

    private String image;

    @Min(value = 0, message = "Rating must be between 0 and 5")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    private Double rating;

    /**
     * Gets the product's ID.
     *
     * @return the ID of the product
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the product's id.
     *
     * @param id the new id of the product
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the product's code.
     *
     * @return the code of the product
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the product's code.
     *
     * @param code the new code of the product
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the new name of the product
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product.
     *
     * @return the description of the product
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the new description of the product
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the new price of the product
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the product in stock.
     *
     * @return the quantity of the product
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity the new quantity of the product
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the inventory status of the product.
     *
     * @return the inventory status of the product
     */
    public ProductInventoryStatus getInventoryStatus() {
        return this.inventoryStatus;
    }

    /**
     * Sets the inventory status of the product.
     *
     * @param inventoryStatus the new inventory status of the product
     */
    public void setInventoryStatus(final ProductInventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    /**
     * Gets the category of the product.
     *
     * @return the category of the product
     */
    public ProductCategory getCategory() {
        return this.category;
    }

    /**
     * Sets the category of the product.
     *
     * @param category the new category of the product
     */
    public void setCategory(final ProductCategory category) {
        this.category = category;
    }

    /**
     * Gets the image URL of the product.
     *
     * @return the image URL of the product
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the image URL of the product.
     *
     * @param image the new image URL of the product
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * Gets the rating of the product.
     *
     * @return the rating of the product
     */
    public Double getRating() {
        return this.rating;
    }

    /**
     * Sets the rating of the product.
     *
     * @param rating the new rating of the product
     */
    public void setRating(final Double rating) {
        this.rating = rating;
    }
}