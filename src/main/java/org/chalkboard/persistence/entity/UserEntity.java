package org.chalkboard.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userid;

  @Column(unique = true)
  private String useremail;

  @Column(name = "user_name")
  private String username;

  @Column(name = "user_age")
  private int userage;

  @Column(name = "password")
  private String userpassword;

  // Mandatory properties
  @Column(name = "is_enabled")
  private boolean isEnabled;

  @Column(name = "account_No_Expired")
  private boolean accountNoExpired;

  @Column(name = "account_No_Locked")
  private boolean accountNoLocked;

  @Column(name ="credential_No_Expired")
  private boolean credentialNoExpired;

  // Unidirectional relationship
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")

  )
  private Set<RoleEntity> roles = new HashSet<>();
}
