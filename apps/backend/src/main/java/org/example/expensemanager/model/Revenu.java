package org.example.expensemanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "revenu")
public class Revenu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idrevenu;

  @Column(length = 100)
  private String titrerevenu;

  @Column
  private double montantrev;

  private LocalDateTime datecreation;

  @Column
  private String desrev;

  @ManyToOne
  @JoinColumn(name = "idcategory")
  private Category category;

  @ManyToOne
  @JoinColumn(name ="iduser")
  private User user;

  public void setIdrevenu(Long idrevenu) {
    this.idrevenu = idrevenu;
  }
}
