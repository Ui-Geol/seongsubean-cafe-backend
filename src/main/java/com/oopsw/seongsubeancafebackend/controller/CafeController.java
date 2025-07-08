package com.oopsw.seongsubeancafebackend.controller;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.service.CafeService;
import com.oopsw.seongsubeancafebackend.vo.RequestCafe;
import com.oopsw.seongsubeancafebackend.vo.ResponseCafe;
import jakarta.validation.Valid;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PostMapping("test")
  public ResponseEntity<String> testCafe(@Valid @RequestBody RequestCafe requestCafe) {

    return ResponseEntity.ok("ok");
  }

  @PostMapping
  public ResponseEntity<Map<String, Long>> createCafe(@Valid @RequestBody RequestCafe requestCafe) {

    CafeDTO cafeDTO = new ModelMapper().map(requestCafe, CafeDTO.class);

    Long resultCafeId = cafeService.createCafe(cafeDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", resultCafeId));
  }

  @GetMapping("/{cafeId}")
  public ResponseEntity<ResponseCafe> getCafe(@PathVariable Long cafeId) {

    CafeDTO resultCafeDTO = cafeService.getCafe(cafeId);

    ResponseCafe responseCafe = new ModelMapper().map(resultCafeDTO, ResponseCafe.class);

    return ResponseEntity.status(HttpStatus.OK).body(responseCafe);

  }

  @PutMapping("/{cafeId}")
  public ResponseEntity<Map<String, Long>> updateCafe(@PathVariable Long cafeId,
      @Valid @RequestBody RequestCafe requestCafe) {

    requestCafe.setCafeId(cafeId);

    CafeDTO cafeDTO = new ModelMapper().map(requestCafe, CafeDTO.class);

    Long ResultCafeId = cafeService.updateCafe(cafeDTO);

    return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", ResultCafeId));
  }


}
