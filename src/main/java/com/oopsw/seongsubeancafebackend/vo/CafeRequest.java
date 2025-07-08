package com.oopsw.seongsubeancafebackend.vo;

import lombok.Data;

@Data
public class CafeRequest {

    private Long cafeId;
    private String cafeName;
    private String cafeAddress;
    private String cafeDetailAddress;
    private String cafeIntroduction;
    private String zipCode;
    private String phoneNumber;
    private String image;
    private String email;
    private boolean status;
}