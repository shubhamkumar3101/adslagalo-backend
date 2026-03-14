package com.adslagalo.controller;

import com.adslagalo.entity.SocialService;
import com.adslagalo.service.SocialServiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/services")
@PreAuthorize("hasRole('ADMIN')")
public class AdminSocialServiceController {

    private final SocialServiceService socialServiceService;

    public AdminSocialServiceController(SocialServiceService socialServiceService) {
        this.socialServiceService = socialServiceService;
    }

    // Create Service under a platform
    @PostMapping("/platform/{platformId}")
    public SocialService createService(@PathVariable Long platformId,
                                       @RequestBody SocialService service) {
        return socialServiceService.createService(platformId, service);
    }

    // Get services of a platform
    @GetMapping("/platform/{platformId}")
    public List<SocialService> getServices(@PathVariable Long platformId) {
        return socialServiceService.getServicesByPlatform(platformId);
    }

    // Update Service
    @PutMapping("/{serviceId}")
    public SocialService updateService(@PathVariable Long serviceId,
                                       @RequestBody SocialService service) {
        return socialServiceService.updateService(serviceId, service);
    }

    // Soft delete Service
    @DeleteMapping("/{serviceId}")
    public String deleteService(@PathVariable Long serviceId) {
        socialServiceService.deleteService(serviceId);
        return "Service deleted successfully";
    }
}