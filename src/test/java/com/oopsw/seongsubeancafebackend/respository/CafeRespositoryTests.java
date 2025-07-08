package com.oopsw.seongsubeancafebackend.respository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest //기본 적으로 각 메서마다 롤백함
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class CafeRespositoryTests {

  @Autowired
  CafeRepository cafeRepository;

  @Test
  @Order(1)
//  @Rollback(false)
//  @Commit
  public void successSaveCafeTest() {
    CafeEntity cafeEntity = CafeEntity.builder()
        .email("owner@coffeehouse.com")
        .cafeName("발할라")
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    CafeEntity resultCafeEntity = cafeRepository.save(cafeEntity);

    assertAll(
        () -> assertThat(resultCafeEntity.getCafeId()).isEqualTo(6L),
        () -> assertThat(resultCafeEntity.getEmail()).isEqualTo("owner@coffeehouse.com"),
        () -> assertThat(resultCafeEntity.getCafeName()).isEqualTo("발할라"),
        () -> assertThat(resultCafeEntity.getZipCode()).isEqualTo("64406"),
        () -> assertThat(resultCafeEntity.getCafeAddress()).isEqualTo("서울특별시 강남구 테헤란로 152"),
        () -> assertThat(resultCafeEntity.getCafeDetailAddress()).isEqualTo("3층 301호"),
        () -> assertThat(resultCafeEntity.getCafeIntroduction()).isEqualTo("조용하고 아늑한 카페입니다"),
        () -> assertThat(resultCafeEntity.getPhoneNumber()).isEqualTo("02-1234-5678"),
        () -> assertThat(resultCafeEntity.getImage()).isEqualTo("/images/cafes/Cafe1.png"),
        () -> assertThat(resultCafeEntity.getIsBusinessDay()).isTrue()
    );
  }

  @Test
  @Order(2)
  public void successFindByCafeIdTest() {
    CafeEntity resultCafeEntity = cafeRepository.findById(1L).get();

    assertAll(
        () -> assertThat(resultCafeEntity.getEmail()).isEqualTo("owner@bluemoon.com"),
        () -> assertThat(resultCafeEntity.getCafeName()).isEqualTo("블루문 카페"),
        () -> assertThat(resultCafeEntity.getZipCode()).isEqualTo("06292"),
        () -> assertThat(resultCafeEntity.getCafeAddress()).isEqualTo("서울특별시 강남구 테헤란로 152"),
        () -> assertThat(resultCafeEntity.getCafeDetailAddress()).isEqualTo("3층 301호"),
        () -> assertThat(resultCafeEntity.getCafeIntroduction()).isEqualTo(
            "조용하고 아늑한 분위기에서 신선한 원두로 내린 커피를 즐길 수 있는 카페입니다. 무료 와이파이와 콘센트가 구비되어 있어 업무나 스터디하기에도 좋습니다."),
        () -> assertThat(resultCafeEntity.getPhoneNumber()).isEqualTo("02-1234-5678"),
        () -> assertThat(resultCafeEntity.getImage()).isEqualTo("/images/cafes/bluemoon-cafe.jpg"),
        () -> assertThat(resultCafeEntity.getIsBusinessDay()).isTrue()
    );
  }

  @Test
  @Order(3)
  public void failFindByCafeIdTest() {

    assertThatThrownBy(() -> cafeRepository.findById(8L).get())
        .isInstanceOf(NoSuchElementException.class);

  }


}
