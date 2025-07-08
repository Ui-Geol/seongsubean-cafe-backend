package com.oopsw.seongsubeancafebackend.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAFE_INFO")
public class CafeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CAFE_ID")
    Long cafeId;

    @Column(name="CAFE_NAME", length = 100, nullable = false)
    String cafeName;
    @Column(name="CAFE_ADDRESS", length = 1000, nullable = false)
    String cafeAddress;
    @Column(name="CAFE_DETAIL_ADDRESS", length = 1000)
    String cafeDetailAddress;
    @Column(name="ZIPCODE", length = 20)
    String zipCode;
    @Column(name="PHONE_NUMBER", length = 20)
    String phoneNumber;
    @Column(name="CAFE_INTRODUCTION", columnDefinition = "TEXT")
    String cafeIntroduction;
    @Column(name="STATUS", nullable = false)
    Boolean status = true;
    @Column(name="IMAGE", length = 200)
    String image;
    @Column(name="CREATED_AT", nullable = false, updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();
    @Column(name="UPDATED_AT")
    LocalDateTime updatedAt;

    @Column(name="EMAIL", length = 100)
    String email;
}