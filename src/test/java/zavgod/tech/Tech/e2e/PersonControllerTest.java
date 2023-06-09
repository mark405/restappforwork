package zavgod.tech.Tech.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import zavgod.tech.Tech.DTO.PersonDTO;
import zavgod.tech.Tech.exceptions.PersonErrorResponse;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PersonControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int randomServerPort;

    @Test
    void should_return_person_wtih_existing_id() {
        Long existingId = 1L;
        PersonDTO personDTO = testRestTemplate.getForObject(
                "http://localhost:" + randomServerPort + "/people/" + existingId, PersonDTO.class);

        assertEquals("person1", personDTO.getFirstName());
        assertEquals("last1", personDTO.getLastName());
        assertEquals(20, personDTO.getAge());

    }

    @Test
    void should_return_error_with_non_existing_id() {
        Long nonExistingId = 99L;
        PersonErrorResponse personErrorResponse = testRestTemplate.getForObject(
                "http://localhost:" + randomServerPort + "/people/" + nonExistingId, PersonErrorResponse.class
        );

        assertEquals("Person with id(" + nonExistingId + ") not found ", personErrorResponse.getMessage());
    }
}
