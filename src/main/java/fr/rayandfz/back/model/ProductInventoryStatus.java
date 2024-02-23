package fr.rayandfz.back.model;

/**
 * Enum representing the inventory status of products in the system.
 * This enumeration helps in tracking the availability of products and
 * facilitating inventory management by providing a quick status check.
 *
 * <ul>
 *     <li>{@code INSTOCK} - Indicates that the product is available in sufficient quantity.</li>
 *     <li>{@code LOWSTOCK} - Indicates that the product is available but in limited quantity, suggesting a need for restocking soon.</li>
 *     <li>{@code OUTOFSTOCK} - Indicates that the product is currently unavailable, with no stock left.</li>
 * </ul>
 */
public enum ProductInventoryStatus {
    INSTOCK,
    LOWSTOCK,
    OUTOFSTOCK,
}
