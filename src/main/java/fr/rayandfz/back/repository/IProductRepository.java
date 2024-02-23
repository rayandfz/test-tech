package fr.rayandfz.back.repository;

import fr.rayandfz.back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository provides the mechanism for storage, retrieval, update,
 * delete and search operation on Product entities.
 *
 * Inherits standard CRUD operations from JpaRepository.
 */
public interface IProductRepository extends JpaRepository<Product, Long> {}