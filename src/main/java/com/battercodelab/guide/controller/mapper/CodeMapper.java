package com.battercodelab.guide.controller.mapper;

import com.battercodelab.guide.controller.dto.GetMainCodeDto;
import com.battercodelab.guide.controller.dto.GetMainCodesDto;
import com.battercodelab.guide.entity.MainCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CodeMapper {

    @Mappings({
            @Mapping(source = "mcodes[].code", target = "code"),
            @Mapping(source = "mcodes[].name", target = "name"),
            @Mapping(source = "mcodes[].expl", target = "expl"),
    })
    List<GetMainCodesDto.Response> toGetMainCodesResponseDto(List<MainCode> mcodes);

    @Mappings({
            @Mapping(source = "mcode.code", target = "code"),
            @Mapping(source = "mcode.name", target = "name"),
            @Mapping(source = "mcode.expl", target = "expl"),
    })
    GetMainCodeDto.Response toGetMainCodeResponseDto(MainCode mcode);
}
