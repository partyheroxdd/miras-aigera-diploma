package kz.iitu.miras_aigera_diploma.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@Table(name = "districts")
@FieldNameConstants
@FieldDefaults(level = AccessLevel.PRIVATE)
public class District extends BaseEntity {

  String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "city_id")
  City city;
}
