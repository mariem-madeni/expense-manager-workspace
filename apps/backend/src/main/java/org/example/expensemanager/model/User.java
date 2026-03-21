package org.example.expensemanager.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  implements UserDetails {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long iduser;

  @Column(length = 50)
  private String firstname;

  @Column(length = 50)
  private String lastname;

  @Column(length = 1)
  private String gender;

  @Column(length = 255)
  private String adress;

  @Column(length = 100, nullable = false, unique = true)
  private String email;

  @Column(length = 255, nullable = false)
  private String password;

  @Column(name = "is_deleted")
  private boolean isDeleted = false;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idrole")
  private Role role;

  @OneToOne
  @JoinColumn(name = "idimage")
  private Image image;

//User details methods
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(("ROLE_"+role.getRolename().name())));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isDeleted;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return !isDeleted;
  }
}
