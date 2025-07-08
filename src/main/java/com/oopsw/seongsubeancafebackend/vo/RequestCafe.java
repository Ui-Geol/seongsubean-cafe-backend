package com.oopsw.seongsubeancafebackend.vo;

import lombok.Data;

@Data
public class RequestCafe {

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
