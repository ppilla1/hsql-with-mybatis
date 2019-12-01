package org.jpmc.service;

import java.util.List;
import java.util.Map;

public interface FeatureOnboardingService {
    void addOrUpdateFeature(String featureId, Boolean isEnabled);
    Boolean isFeatureEnabled(String featureId);
    void addOrUpdateConfiguration(String featureId, String tenantId, Map<String, Object> config);
    Map<String, Object> fetchConfig(String featureId, String tenantId);
    void subscribeTenantToFeature(String tenantId, String featureId, String role);
    void unsubscribeTenantFromFeature(String tenantId, String featureId);
    void updateTenantRoleForFeature(String tenantId, String featureId, String role);
    List<Map<String, Object>> fetchTenantSubscriptions(String tenantId);
    String fetchTenantRoleForSubscription(String tenantId, String featureId);
}
