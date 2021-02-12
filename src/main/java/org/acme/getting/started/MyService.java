package org.acme.getting.started;

import io.quarkus.vault.VaultSystemBackendEngine;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyService {

    @Inject
    VaultSystemBackendEngine vault;

    public boolean isInitialized() {
        return vault.healthStatus().isInitialized();
    }
}