package com.oopsw.seongsubeancafebackend.respository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class CafeRespositoryTests {

  @Autowired
  CafeRepository cafeRepository;

  @Test
  public void successCreateCafeTest() {
    CafeEntity cafeEntity = CafeEntity.builder()
        .email("owner@coffeehouse.com")
        .cafeName("발할라")
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    CafeEntity result = cafeRepository.save(cafeEntity);

    assertAll(
        () -> assertThat(result.getEmail()).isEqualTo("owner@coffeehouse.com"),
        () -> assertThat(result.getCafeName()).isEqualTo("발할라"),
        () -> assertThat(result.getZipCode()).isEqualTo("64406"),
        () -> assertThat(result.getCafeAddress()).isEqualTo("서울특별시 강남구 테헤란로 152"),
        () -> assertThat(result.getCafeDetailAddress()).isEqualTo("3층 301호"),
        () -> assertThat(result.getCafeIntroduction()).isEqualTo("조용하고 아늑한 카페입니다"),
        () -> assertThat(result.getPhoneNumber()).isEqualTo("02-1234-5678"),
        () -> assertThat(result.getImage()).isEqualTo("/images/cafes/Cafe1.png"),
        () -> assertThat(result.getStatus()).isTrue()
    );
  }


}
