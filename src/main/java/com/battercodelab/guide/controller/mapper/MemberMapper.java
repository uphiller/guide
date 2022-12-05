package com.battercodelab.guide.controller.mapper;

import com.battercodelab.guide.controller.dto.LoginDto;
import com.battercodelab.guide.controller.dto.SignupDto;
import com.battercodelab.guide.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mappings({
            @Mapping(source = "member.id", target = "id"),
            @Mapping(source = "member.name", target = "name"),
            @Mapping(source = "token", target = "token"),
    })
    SignupDto.Response toSignupResponseDto(Member member, String token);

    @Mappings({
            @Mapping(source = "member.id", target = "id"),
            @Mapping(source = "member.name", target = "name"),
            @Mapping(source = "token", target = "token"),
    })
    LoginDto.Response toLoginResponseDto(Member member, String token);
}
