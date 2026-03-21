package org.example.expensemanager.repository;

import org.example.expensemanager.model.Revenu;
import org.example.expensemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevenuRepository extends JpaRepository<Revenu,Long> {

}
