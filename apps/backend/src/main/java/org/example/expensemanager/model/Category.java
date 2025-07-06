package org.example.expensemanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="category")
public class Category {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcategory;

  @Column(length = 100)
  private String descategory;

  private LocalDateTime datecreation;


}
