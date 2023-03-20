package zavgod.tech.Tech.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import zavgod.tech.Tech.models.Person;
import zavgod.tech.Tech.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PeopleServiceUnitTest {
    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleService peopleService;

    @Test
    void findOne_should_return_person() {
        Person person = buildTestPerson();
        when(peopleRepository.findById(1L)).thenReturn(Optional.of(person));
        Person returnedPerson = peopleService.findOne(1L);

        assertEquals(person.getId(), returnedPerson.getId());

        verify(peopleRepository).findById(1L);
    }

    @Test
    void getAgeById_should_return_age_of_person() {
        Person person = buildTestPerson();
        when(peopleRepository.findById(1L)).thenReturn(Optional.of(person));

        Integer age = peopleService.getAgeById(1L);

        assertEquals(person.getAge(), age);
    }

    private Person buildTestPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("test");
        person.setLastName("testlast");
        person.setAge(30);

        return person;
    }
}