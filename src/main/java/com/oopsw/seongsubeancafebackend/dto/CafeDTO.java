package com.oopsw.seongsubeancafebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CafeDTO {

  private Long cafeId;
  private String email;
  private String cafeName;
  private String zipCode;
  private String cafeAddress;
  private String cafeDetailAddress;
  private String phoneNumber;
  private String cafeIntroduction;
  private String image;

}
