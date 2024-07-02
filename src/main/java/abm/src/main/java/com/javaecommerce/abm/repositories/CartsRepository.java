package com.javaecommerce.abm.repositories;

import com.javaecommerce.abm.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsRepository extends JpaRepository<Cart, Long> {
}