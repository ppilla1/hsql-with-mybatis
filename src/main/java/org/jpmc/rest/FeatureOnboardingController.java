package org.jpmc.rest;

import lombok.extern.slf4j.Slf4j;
import org.jpmc.service.FeatureOnboardingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
class FeatureOnboardingController {

    private final FeatureOnboardingService featureOnboardingService;

    FeatureOnboardingController(FeatureOnboardingService featureOnboardingService) {
        this.featureOnboardingService = featureOnboardingService;
    }

    @PostMapping({"/api/v1_0/feature/{featureId}/{isenabled}"})
    void addOrUpdateFeature(@PathVariable("featureId") String featureId, @PathVariable("isenabled") Boolean isEnabled){
        featureOnboardingService.addOrUpdateFeature(featureId, isEnabled);
    }

    @GetMapping({"/api/v1_0/feature/{featureId}"})
    boolean isFeatureEnabled(@PathVariable("featureId") String featureId){
        return featureOnboardingService.isFeatureEnabled(featureId);
    }

    @PostMapping({"/api/v1_0/onboarding/{featureId}/{tenantId}"})
    void addOrUpdateFeatureOnboarding(@PathVariable("featureId") String featureId, @PathVariable("tenantId") String tenantId, @RequestBody Map<String, Object> config){
        featureOnboardingService.addOrUpdateConfiguration(featureId, tenantId, config);
    }

    @GetMapping("/api/v1_0/onboarding/{featureId}/{tenantId}/fetch")
    Map<String, Object> fetchConfiguration(String featureId, String tenantId){
        Map<String, Object> config = featureOnboardingService.fetchConfig(featureId, tenantId);
        return config;
    }

    @PostMapping("/api/v1_0/subscribe/{tenantId}/{featureId}")
    void subscribeTenant(@PathVariable("tenantId") String tenantId, @PathVariable("featureId") String featureId, @RequestBody String role){
        featureOnboardingService.subscribeTenantToFeature(tenantId, featureId, role);
    }

    @DeleteMapping("/api/v1_0/unsubscribe/{tenantId}/{featureId}")
    void unsubscribeTenant(@PathVariable("tenantId") String tenantId, @PathVariable("featureId") String featureId){
        featureOnboardingService.unsubscribeTenantFromFeature(tenantId, featureId);
    }

    @PostMapping("/api/v1_0/updaterole/{tenantId}/{featureId}")
    void updateTenantRole(@PathVariable("tenantId") String tenantId, @PathVariable("featureId") String featureId, @RequestBody String role){
        featureOnboardingService.updateTenantRoleForFeature(tenantId, featureId, role);
    }

    @GetMapping("/api/v1_0/all-subscriptions/{tenantId}")
    List<Map<String, Object>> fetchAllSubscriptions(@PathVariable("tenantId") String tenantId){
        return featureOnboardingService.fetchTenantSubscriptions(tenantId);
    }

    @GetMapping("/api/v1_0/subscription-role/{tenantId}/{featureId}")
    String fetchTenantSubscriptionRole(@PathVariable("tenantId") String tenantId, @PathVariable("featureId") String featureId){
        return featureOnboardingService.fetchTenantRoleForSubscription(tenantId, featureId);
    }
}
