package com.battercodelab.guide.service;

import com.battercodelab.guide.controller.dto.SignupDto;
import com.battercodelab.guide.exception.BusinessException;
import com.battercodelab.guide.exception.ErrorCode;
import com.battercodelab.guide.entity.Member;
import com.battercodelab.guide.entity.MemberRole;
import com.battercodelab.guide.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member registerUser(SignupDto.Request requestDto) {
        String id = requestDto.getId();
        Optional<Member> existUser = memberRepository.findById(id);
        if(existUser.isPresent()) throw new BusinessException(ErrorCode.conflict);
        String password = passwordEncoder.encode(requestDto.getPassword());
        MemberRole role = MemberRole.USER;
        Member member = new Member(id, password, requestDto.getName(), role);
        memberRepository.save(member);

        return member;
    }

}
