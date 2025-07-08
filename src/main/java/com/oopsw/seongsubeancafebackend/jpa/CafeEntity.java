package com.oopsw.seongsubeancafebackend.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CAFE_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CafeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cafeId;

  @Column(name = "EMAIL", length = 100)
  private String email;

  @Column(name = "cafe_name", nullable = false, length = 100)
  private String cafeName;

  @Column(name = "zipcode", length = 20)
  private String zipCode;

  @Column(name = "cafe_address", nullable = false, length = 255)
  private String cafeAddress;

  @Column(name = "cafe_detail_address", length = 255)
  private String cafeDetailAddress;

  @Column(name = "phone_number", length = 20)
  private String phoneNumber;

  @Column(name = "cafe_introduction", columnDefinition = "TEXT")
  private String cafeIntroduction;

  @Column(name = "image", length = 200)
  private String image;

  @Column(name = "status", nullable = false)
  private Boolean status = true;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist

  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}