package com.battercodelab.guide.controller;

import com.battercodelab.guide.controller.dto.SetMainMenuDto;
import com.battercodelab.guide.controller.dto.SetSubMenuDto;
import com.battercodelab.guide.controller.mapper.CodeMapper;
import com.battercodelab.guide.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class CodeController {

    private final CodeService codeService;
    private final CodeMapper codeMapper;

    @PostMapping(value = "/main/codes")
    public ResponseEntity<?> setMainCode(@RequestBody SetMainMenuDto.Request request) {
        codeService.setMainCode(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/main/codes")
    public ResponseEntity<?> getMainCodes() {
        return new ResponseEntity<>(codeMapper.toGetMainCodesResponseDto(codeService.getMainCodes()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/main/codes/{code}")
    public ResponseEntity<?> getMainCode(@PathVariable("code") String code) {
        return new ResponseEntity<>(codeMapper.toGetMainCodeResponseDto(codeService.getMainCode(code)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/sub/codes")
    public ResponseEntity<?> setSubCode(@RequestBody SetSubMenuDto.Request request) {
        codeService.setSubCode(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
