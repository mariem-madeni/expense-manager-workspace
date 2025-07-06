package org.example.expensemanager.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="depense")
public class Depense {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iddepense;


  @Column(length = 100)
  private String titredepense ;

  @Column
  private double montantdep;

  private LocalDateTime datecreation;

  @Column
  private String descriptiondep;


  @ManyToOne
  @JoinColumn(name = "idcategory")
  private Category category;

  @ManyToOne
  @JoinColumn(name ="iduser")
  private User user;

}
