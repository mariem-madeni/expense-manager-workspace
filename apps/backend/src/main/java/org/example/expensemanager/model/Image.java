package org.example.expensemanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "image")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idimage ;

  @Lob
  @Column(name="imagedata", columnDefinition = "bytea")
  private byte[] imagedata;


  private LocalDateTime datecreation;


  @OneToOne
  @JoinColumn(name ="iduser")
  private User user;


}
