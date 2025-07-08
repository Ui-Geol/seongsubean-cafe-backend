package com.oopsw.seongsubeancafebackend.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestCafe {

  //null값이 존재하므로 유효성 검사는 service에서 진행
  private Long cafeId;
  @NotNull
  @Size(max = 100)
  private String email;
  @NotNull
  @Size(max = 100)
  private String cafeName;
  @NotNull
  @Size(max = 20)
  private String zipCode;
  @NotNull
  @Size(max = 255)
  private String cafeAddress;
  @NotNull
  @Size(max = 255)
  private String cafeDetailAddress;
  @NotNull
  @Size(max = 20)
  private String phoneNumber;
  @NotNull
  @Size(max = 3000)
  private String cafeIntroduction;
  @NotNull
  @Size(max = 200)
  private String image;
  private Boolean isBusinessDay;
}
