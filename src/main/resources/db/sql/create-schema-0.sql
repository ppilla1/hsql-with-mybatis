CREATE TABLE ONBOARDING_CONFIGURATIONS(
    FEATURE_ID VARCHAR(70),
    TENANT_ID VARCHAR(70),
    CONFIGURATION LONGVARCHAR,
    PRIMARY KEY (FEATURE_ID, TENANT_ID)
);
