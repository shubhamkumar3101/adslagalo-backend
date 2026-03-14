package com.adslagalo.repository;

import com.adslagalo.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    Optional<Platform> findByName(String name);

    List<Platform> findByActiveTrue();
}