package com.adslagalo.service;

import com.adslagalo.entity.Platform;
import com.adslagalo.repository.PlatformRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformService {

    private final PlatformRepository platformRepository;

    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    // Create Platform
    public Platform createPlatform(Platform platform) {

        platformRepository.findByName(platform.getName())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Platform already exists");
                });

        return platformRepository.save(platform);
    }

    // Get all active platforms
    public List<Platform> getAllActivePlatforms() {
        return platformRepository.findByActiveTrue();
    }

    // Update Platform
    public Platform updatePlatform(Long id, Platform updatedPlatform) {

        Platform platform = platformRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Platform not found"));

        platform.setName(updatedPlatform.getName());
        platform.setDescription(updatedPlatform.getDescription());

        return platformRepository.save(platform);
    }

    // Soft Delete Platform
    public void deletePlatform(Long id) {

        Platform platform = platformRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Platform not found"));

        platform.setActive(false);

        platformRepository.save(platform);
    }
}