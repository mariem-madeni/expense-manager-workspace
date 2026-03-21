package org.example.expensemanager.repository;

import org.example.expensemanager.model.Depense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepenseRepository extends JpaRepository<Depense,Long> {
}
