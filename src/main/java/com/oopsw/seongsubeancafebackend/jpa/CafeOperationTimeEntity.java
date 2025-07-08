package com.oopsw.seongsubeancafebackend.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name="OPERATION_TIME")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CafeOperationTimeEntity {
  @jakarta.persistence.Id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long CafeOperationTimeId;
  @Column(name = "weekday", nullable = false, length = 10)
  private String weekDay;
  @Column(name = "opentime", nullable = false, length = 10)
  private String openTime;
  @Column(name = "closetime", nullable = false, length = 10)
  private String closeTime;

}
