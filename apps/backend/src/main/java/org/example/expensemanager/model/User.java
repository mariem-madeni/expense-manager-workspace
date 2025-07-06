package org.example.expensemanager.model;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iduser;

  @Column(length =50)
  private String firstname;

  @Column(length =50)
  private String lastname;

  @Column(length =1)
  private String gender;

  @Column(length =255)
  private String adress;

  @Column(length =100, nullable = false, unique =true)
  private String email;

  @Column(length =255 , nullable = false)
  private String password;

  @ManyToOne
  @JoinColumn(name = "idrole")
  private Role role;

  @OneToOne
  @JoinColumn(name ="idimage")
  private Image image;

}
