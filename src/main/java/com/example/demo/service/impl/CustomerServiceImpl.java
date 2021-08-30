package com.example.demo.service.impl;

import com.example.demo.configuration.KeycloakConfig;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.admin.client.resource.ProtocolMappersResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.ProtocolMapperRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllUsers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void addUser(User user) {
        UsersResource usersResource = KeycloakConfig.getInstance().realm("SpringBootKeycloak").users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(user.isEnabled());
        kcUser.setEmailVerified(false);

        Map<String,List<String>> attributes = new HashMap<>();

        attributes.put("phoneNumber",List.of(user.getPhoneNumber()));
        attributes.put("phoneNumberVerified",List.of(user.getPhoneNumberVerified()));
        attributes.put("emailVerified",List.of(user.getEmailVerified()));

        kcUser.setAttributes(attributes);
        kcUser.setGroups();

        Response response = usersResource.create(kcUser);
        System.out.println("Status code" + response.getStatus());

    }
    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

}
