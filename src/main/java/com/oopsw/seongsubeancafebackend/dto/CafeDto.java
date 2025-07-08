package com.oopsw.seongsubeancafebackend.dto;

import lombok.Data;

@Data
public class CafeDto {

  private Long id;
  private String email;
  private String cafeName;
  private String zipCode;
  private String cafeAddress;
  private String cafeDtailAddress;
  private String phoneNumber;
  private String cafeIntroduction;
  private String image;
  private String totalRating;

}
