package com.example.demo.services;

import growthbook.sdk.java.FeatureFetchException;
import growthbook.sdk.java.FeatureRefreshCallback;
import growthbook.sdk.java.GBFeaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicEncryptedFeaturesService extends GBFeaturesRepository {
    @Autowired
    public BasicEncryptedFeaturesService() {
        super(
            "https://cdn.growthbook.io/api/features/sdk-862b5mHcP9XPugqD",
            "BhB1wORFmZLTDjbvstvS8w==",
            15
        );

        this.onFeaturesRefresh(new FeatureRefreshCallback() {
            @Override
            public void onRefresh(String featuresJson) {
                System.out.println("🔵 BasicEncryptedFeaturesService -> Features have been refreshed");
                System.out.println(featuresJson);
            }
        });

        try {
            this.initialize();
        } catch (FeatureFetchException e) {
            this.handleError(e);
        }
    }

    void handleError(FeatureFetchException e) {
        e.printStackTrace();

        switch (e.getErrorCode()) {
            case NO_RESPONSE_ERROR -> {
                // Handle NO_RESPONSE_ERROR
            }

            case CONFIGURATION_ERROR, UNKNOWN -> {
                throw new RuntimeException(e);
            }
        }
    }

    private BasicEncryptedFeaturesService(String endpoint, String encryptionKey, Integer ttlSeconds) {
        super(endpoint, encryptionKey, ttlSeconds);
    }
}
