package com.oopsw.seongsubeancafebackend.controller;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.service.CafeService;
import com.oopsw.seongsubeancafebackend.vo.CafeRequest;
import com.oopsw.seongsubeancafebackend.vo.CafeResponse;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public final Environment environment;
    public final CafeService cafeService;
    public CafeController(Environment environment, CafeService cafeService){
        this.environment = environment;
        this.cafeService = cafeService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> addCafe(
            @RequestBody CafeRequest cafeRequest){
        CafeDTO cafeDTO = cafeService.addCafe(new ModelMapper().map(cafeRequest, CafeDTO.class));
        Long cafeId = cafeDTO.getCafeId();
        return ResponseEntity.ok(Map.of("cafeId", cafeId));
    }

    @GetMapping("/{cafeId}")
    public ResponseEntity<CafeResponse> getCafeByCafeId(
            @PathVariable Long cafeId) {
        CafeDTO cafeDTO = cafeService.findByCafeId(cafeId);
        CafeResponse cafeResponse = new ModelMapper().map(cafeDTO, CafeResponse.class);
        return ResponseEntity.ok(cafeResponse);
    }

    @PutMapping("/{cafeId}")
    public ResponseEntity<CafeResponse> updateCafe(
            @PathVariable Long cafeId,
            @RequestBody CafeRequest cafeRequest
    ) {
        CafeDTO updatedDTO = cafeService.updateCafe(cafeId, new ModelMapper().map(cafeRequest, CafeDTO.class));
        CafeResponse response = new ModelMapper().map(updatedDTO, CafeResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cafeId}")
    public ResponseEntity<Map<String, String>> deleteCafe(@PathVariable Long cafeId) {
        cafeService.deleteCafe(cafeId);
        return ResponseEntity.ok(Map.of("message", "Cafe deleted successfully"));
    }

}