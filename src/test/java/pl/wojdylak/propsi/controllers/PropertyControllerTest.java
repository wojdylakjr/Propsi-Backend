package pl.wojdylak.propsi.controllers;


import javafx.beans.binding.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wojdylak.propsi.model.Property;
import pl.wojdylak.propsi.repository.PropertyRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    public void testGetAllProperties() {
        // Given
        Property property1 = new Property("test address 1");
        Property property2 = new Property("test address 2");
        propertyRepository.save(property1);
        propertyRepository.save(property2);

        // When
        ResponseEntity<List<Property>> response = restTemplate.exchange("/api/properties", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        // Then
        assert ((response.getStatusCode()).is2xxSuccessful());
        assert (response.getBody()).contains(property1);
    }

}

