package org.acme.getting.started;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.vault.VaultSystemBackendEngine;
import io.quarkus.vault.sys.VaultHealthStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Inject
    MyService myService;

    @BeforeAll
    public static void mockVaultSystemBackendEngine() {
        System.out.println("in mockVaultSystemBackendEngine ========> " + Thread.currentThread().getContextClassLoader());
        VaultSystemBackendEngine sys = Mockito.mock(VaultSystemBackendEngine.class);
        VaultHealthStatus vaultHealthStatus = new VaultHealthStatus();
        vaultHealthStatus.setInitialized(true);
        Mockito.when(sys.healthStatus()).thenReturn(vaultHealthStatus);
        QuarkusMock.installMockForType(sys, VaultSystemBackendEngine.class);
    }

    @Test
    public void testMyService() {
        Assertions.assertTrue(myService.isInitialized());
    }
}