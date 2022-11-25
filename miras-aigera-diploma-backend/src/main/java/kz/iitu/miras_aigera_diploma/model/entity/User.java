package kz.iitu.miras_aigera_diploma.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@Entity
@Builder
@ToString
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "fullname", nullable = false)
  private String fullName;

  @Column(name = "address")
  private String address;

  @Column(name = "location")
  private String location;

  @Column(name = "position")
  private String position;

  @Column(name = "phone", unique = true, nullable = false)
  private String phone;

  @Column(name = "password")
  private String password;


  @ManyToMany
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"
      ),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"
      )
  )
  @JsonIgnore
  private Set<Role> roles = new HashSet<>();

}
