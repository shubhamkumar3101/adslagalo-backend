package com.adslagalo.repository;

import com.adslagalo.entity.SocialService;
import com.adslagalo.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocialServiceRepository extends JpaRepository<SocialService, Long> {

    Optional<SocialService> findByNameAndPlatform(String name, Platform platform);

    List<SocialService> findByPlatformAndActiveTrue(Platform platform);

    List<SocialService> findByActiveTrue();
}