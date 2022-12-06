package com.battercodelab.guide.service;

import com.battercodelab.guide.controller.dto.SetMainMenuDto;
import com.battercodelab.guide.controller.dto.SetSubMenuDto;
import com.battercodelab.guide.entity.MainCode;
import com.battercodelab.guide.entity.repository.MainCodeRepository;
import com.battercodelab.guide.exception.BusinessException;
import com.battercodelab.guide.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeService {

    private final MainCodeRepository mainCodeRepository;

    @CacheEvict(value="mCode", allEntries=true)
    @Transactional
    public void setMainCode(SetMainMenuDto.Request request) {
        int codeCnt = getMainCodes().size();
        String code = String.format("M%s", codeCnt + 1);
        mainCodeRepository.save(
                MainCode.builder()
                        .code(code)
                        .name(request.getName())
                        .expl(request.getExpl())
                        .build()
        );
    }

    public void setSubCode(SetSubMenuDto.Request request) {
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "mCode")
    public MainCode getMainCode(String code) {
        MainCode mainCode = mainCodeRepository.findByCode(code).orElseThrow(() -> new BusinessException(ErrorCode.data_is_empty));
        return mainCode;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "mCode")
    public List<MainCode> getMainCodes() {
        return mainCodeRepository.findAll();
    }

}
