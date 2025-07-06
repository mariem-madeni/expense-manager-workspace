package org.example.expensemanager.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idrole;


  @Enumerated(EnumType.STRING)
  @Column(length = 50, nullable = false)
  private RoleName rolename;




}
