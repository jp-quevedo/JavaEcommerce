package com.javaecommerce.abm.repositories;

import com.javaecommerce.abm.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Long> {
}