package com.adslagalo.service;

import com.adslagalo.entity.Platform;
import com.adslagalo.entity.SocialService;
import com.adslagalo.repository.PlatformRepository;
import com.adslagalo.repository.SocialServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialServiceService {

    private final SocialServiceRepository socialServiceRepository;
    private final PlatformRepository platformRepository;

    public SocialServiceService(SocialServiceRepository socialServiceRepository,
                                PlatformRepository platformRepository) {
        this.socialServiceRepository = socialServiceRepository;
        this.platformRepository = platformRepository;
    }

    // Create Service
    public SocialService createService(Long platformId, SocialService service) {

        Platform platform = platformRepository.findById(platformId)
                .orElseThrow(() -> new IllegalArgumentException("Platform not found"));

        socialServiceRepository.findByNameAndPlatform(service.getName(), platform)
                .ifPresent(s -> {
                    throw new IllegalArgumentException("Service already exists for this platform");
                });

        service.setPlatform(platform);

        return socialServiceRepository.save(service);
    }

    // Get services of a platform
    public List<SocialService> getServicesByPlatform(Long platformId) {

        Platform platform = platformRepository.findById(platformId)
                .orElseThrow(() -> new IllegalArgumentException("Platform not found"));

        return socialServiceRepository.findByPlatformAndActiveTrue(platform);
    }

    // Update Service
    public SocialService updateService(Long serviceId, SocialService updatedService) {

        SocialService service = socialServiceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        service.setName(updatedService.getName());
        service.setDescription(updatedService.getDescription());
        service.setPrice(updatedService.getPrice());

        return socialServiceRepository.save(service);
    }

    // Soft delete
    public void deleteService(Long serviceId) {

        SocialService service = socialServiceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        service.setActive(false);

        socialServiceRepository.save(service);
    }
}