package com.adslagalo.controller;

import com.adslagalo.entity.Platform;
import com.adslagalo.service.PlatformService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/platforms")
@PreAuthorize("hasRole('ADMIN')")
public class AdminPlatformController {

    private final PlatformService platformService;

    public AdminPlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    // Create Platform
    @PostMapping
    public Platform createPlatform(@RequestBody Platform platform) {
        return platformService.createPlatform(platform);
    }

    // Get All Active Platforms
    @GetMapping
    public List<Platform> getAllPlatforms() {
        return platformService.getAllActivePlatforms();
    }

    // Update Platform
    @PutMapping("/{id}")
    public Platform updatePlatform(@PathVariable Long id,
                                   @RequestBody Platform platform) {
        return platformService.updatePlatform(id, platform);
    }

    // Soft Delete Platform
    @DeleteMapping("/{id}")
    public String deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
        return "Platform deleted successfully";
    }
}