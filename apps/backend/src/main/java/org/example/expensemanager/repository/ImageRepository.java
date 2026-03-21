package org.example.expensemanager.repository;

import org.example.expensemanager.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
