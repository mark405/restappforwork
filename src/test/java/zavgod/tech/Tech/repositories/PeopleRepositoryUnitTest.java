package zavgod.tech.Tech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import zavgod.tech.Tech.models.Person;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PeopleRepositoryUnitTest {
    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    void findAll_should_return_people_list() {
        List<Person> people = peopleRepository.findAll();

        assertEquals(5, people.size());
    }

    @Test
    void findById_should_return_person() {
        Optional<Person> personOptional = peopleRepository.findById(1L);

        assertTrue(personOptional.isPresent());
    }

    @Test
    void save_should_insert_new_person() {
        Person person = new Person();

        person.setFirstName("mark");
        person.setLastName("marklast");
        person.setAge(19);

        Person persistedPerson = peopleRepository.save(person);

        assertNotNull(persistedPerson);
        assertEquals(6, persistedPerson.getId());
    }

    @Test
    void save_should_update_existing_person() {
        final String newName = "marknew";
        final String newLastName = "marklastnew";
        Person existingPerson = new Person();

        existingPerson.setId(3L);
        existingPerson.setFirstName(newName);
        existingPerson.setLastName(newLastName);

        Person updatedPerson = peopleRepository.save(existingPerson);

        assertNotNull(updatedPerson);
        assertEquals(newName, updatedPerson.getFirstName());
        assertEquals(newLastName, updatedPerson.getLastName());
    }

    @Test
    void deleteById_should_delete_person() {
        peopleRepository.deleteById(2L);
        Optional<Person> deletedPerson = peopleRepository.findById(2L);
        assertFalse(deletedPerson.isPresent());
    }
}