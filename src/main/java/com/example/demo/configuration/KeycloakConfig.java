package com.example.demo.configuration;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {
    static Keycloak keycloak = null;

    public final static String serverUrl = "http://localhost:8180/auth";
    public final static String realm = "master";
    public final static String clientId = "admin-cli";
    public final static String clientSecret = "b1c98a9e-3017-452c-882a-7cc4ffaa20d0";
    public final static String userName = "thanhnv75";
    public final static String password = "thanhnv75";

    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build()).build();
        }
        return keycloak;
    }
}
