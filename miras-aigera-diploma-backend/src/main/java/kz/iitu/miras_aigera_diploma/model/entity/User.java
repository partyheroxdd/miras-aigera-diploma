package kz.iitu.miras_aigera_diploma.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity {

  String firstname;

  String lastname;

  String midname;

  String username;

  String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  City city;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id")
  District district;

  String password;

  String address;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  Role role;
}
