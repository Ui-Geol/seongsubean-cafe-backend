package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class CafeServiceTests {

  @Autowired
  private CafeService cafeService;

  @Test
  @Order(1)
  public void successCreateCafeTest() {

    CafeDTO cafeDTO = CafeDTO.builder()
        .email("owner@coffeehouse.com")
        .cafeName("발할라")
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    Long cafeId = cafeService.createCafe(cafeDTO);

    Assertions.assertThat(cafeId).isEqualTo(1L);

  }


}
