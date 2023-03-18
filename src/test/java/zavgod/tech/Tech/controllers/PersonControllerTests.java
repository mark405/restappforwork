package zavgod.tech.Tech.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import zavgod.tech.Tech.DTO.PersonDTO;
import zavgod.tech.Tech.models.Person;
import zavgod.tech.Tech.services.PeopleService;
import zavgod.tech.Tech.utils.PersonErrorResponse;
import zavgod.tech.Tech.utils.PersonNotFoundException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTests {
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
//    @MockBean
//    private PeopleService peopleService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void should_return_person_with_existing_id() throws Exception {
//        Person person = buildTestPerson();
//        when(peopleService.findOne(1L)).thenReturn(person);
//
//        mockMvc.perform(get("/people/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
//                .andExpect(jsonPath("$.lastName").value(person.getLastName()))
//                .andExpect(jsonPath("$.age").value(person.getAge()));
//    }
//
//    @Test
//    void should_return_error_with_non_existing_id() throws Exception {
//        Long id = 99L;
//        when(peopleService.findOne(id)).thenThrow(new PersonNotFoundException("Person not found with id " + id));
//
//        mockMvc.perform(get("/people/" + id))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Person not found with id " + id));
//    }
//
//    private Person buildTestPerson() {
//        Person person = new Person();
//        person.setId(1L);
//        person.setFirstName("test");
//        person.setLastName("testlast");
//        person.setAge(30);
//
//        return person;
//    }
}
