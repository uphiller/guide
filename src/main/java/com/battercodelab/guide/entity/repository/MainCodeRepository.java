package com.battercodelab.guide.entity.repository;

import com.battercodelab.guide.entity.MainCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainCodeRepository extends JpaRepository<MainCode, String> {
    Optional<MainCode> findByCode(String code);
}
