package org.jpmc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jpmc.feature.repository.FeatureRepository;
import org.jpmc.feature.repository.TenantRepository;
import org.jpmc.onboarding.repository.OnboardingRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
class FeatureOnboarding implements FeatureOnboardingService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final FeatureRepository featureRepository;
    private final OnboardingRepository onboardingRepository;
    private final TenantRepository tenantRepository;

    FeatureOnboarding(FeatureRepository featureRepository, OnboardingRepository onboardingRepository, TenantRepository tenantRepository) {
        this.featureRepository = featureRepository;
        this.onboardingRepository = onboardingRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public void addOrUpdateFeature(String featureId, Boolean isEnabled) {
        Map<String, Object> params = new HashMap<>();
        params.put("F_ID", featureId);
        params.put("EN", isEnabled ? 1 : 0);
        params.put("DUAL", "INFORMATION_SCHEMA.SYSTEM_USERS");
        featureRepository.addOrUpdate(params);
    }

    @Override
    public Boolean isFeatureEnabled(String featureId) {
        Map<String, Object> params = new HashMap<>();
        params.put("F_ID", featureId);
        List<Map<String, Object>> resultset = featureRepository.fetch(params);

        Boolean isEnabled = false;
        Integer status = 0;
        if (!resultset.isEmpty()){
            status = Integer.class.cast(resultset.get(0).get("IS_ENABLED"));
        }
        isEnabled = status <= 0 ? false : true;

        return isEnabled;
    }

    @Override
    public void addOrUpdateConfiguration(String featureId, String tenantId, Map<String, Object> config) {
        Map<String, Object> params = new HashMap<>();
        params.put("F_ID", featureId);
        params.put("T_ID", tenantId);

        String configPayload  = null;
        try {
            configPayload = mapper.writeValueAsString(config);
        } catch (JsonProcessingException e) {
            log.warn("{}", e.getMessage(), e);
        }
        params.put("CONFIG", configPayload);
        onboardingRepository.addOrUpdate(params);
    }

    @Override
    public Map<String, Object> fetchConfig(String featureId, String tenantId){
        Map<String, Object> params = new HashMap<>();
        params.put("F_ID", featureId);
        params.put("T_ID", tenantId);

        List<Map<String, Object>> configPayloads = onboardingRepository.fetch(params);

        Map<String, Object> cofig = null;

        if (!configPayloads.isEmpty()){

            try {
                cofig = mapper.readValue(configPayloads.get(0).get("CONFIGURATION").toString(), HashMap.class);
            } catch (IOException e) {
                log.warn("{}", e.getMessage(), e);
            }

        }

        return cofig;
    }

    @Override
    public void subscribeTenantToFeature(String tenantId, String featureId, String role) {
        Map<String, Object> params =  new HashMap<>();
        params.put("T_ID", tenantId);
        params.put("F_ID", featureId);
        params.put("ROLE", role);

        tenantRepository.add(params);
    }

    @Override
    public void unsubscribeTenantFromFeature(String tenantId, String featureId) {
        Map<String, Object> params =  new HashMap<>();
        params.put("T_ID", tenantId);
        params.put("F_ID", featureId);

        tenantRepository.delete(params);
    }

    @Override
    public void updateTenantRoleForFeature(String tenantId, String featureId, String role) {
        Map<String, Object> params =  new HashMap<>();
        params.put("T_ID", tenantId);
        params.put("F_ID", featureId);
        params.put("ROLE", role);

        tenantRepository.update(params);
    }

    @Override
    public List<Map<String, Object>> fetchTenantSubscriptions(String tenantId) {
        Map<String, Object> params =  new HashMap<>();
        params.put("T_ID", tenantId);

        return tenantRepository.fetchAll(params);
    }

    @Override
    public String fetchTenantRoleForSubscription(String tenantId, String featureId) {
        Map<String, Object> params =  new HashMap<>();
        params.put("T_ID", tenantId);
        params.put("F_ID", featureId);

        Map<String, Object> tenant = tenantRepository.fetchById(params);
        String role = null;

        if (null != tenant && !tenant.isEmpty()){
            role = tenant.get("TENANT_ROLE").toString();
        }

        return role;
    }
}
