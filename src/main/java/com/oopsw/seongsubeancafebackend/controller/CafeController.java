package com.oopsw.seongsubeancafebackend.controller;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.service.CafeService;
import com.oopsw.seongsubeancafebackend.vo.RequestCafe;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cafes")
public class CafeController {

  private CafeService cafeService;

  //Environment 고려하기
  @Autowired
  public CafeController(CafeService cafeService) {
    this.cafeService = cafeService;
  }

  @GetMapping("/")
  public ResponseEntity<Map<String, String>> getCafe() {
    return ResponseEntity.ok(Map.of("message", "ok"));
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> postCafe(@RequestBody RequestCafe requestCafe) {

    CafeDTO cafeDTO = new ModelMapper().map(requestCafe, CafeDTO.class);

    try {
      Long resultCafeId = cafeService.createCafe(cafeDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", resultCafeId));
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Map.of("message", "카페 생성이 실패하였습니다"));
    }
  }

}
