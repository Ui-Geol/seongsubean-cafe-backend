package com.oopsw.seongsubeancafebackend.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeDTO {
    Long cafeId;

    String cafeName;
    String cafeAddress;
    String cafeDetailAddress;
    String zipCode;
    String phoneNumber;
    String cafeIntroduction;
    Boolean status;
    String image;
    LocalDateTime updatedAt;

    String email;
}