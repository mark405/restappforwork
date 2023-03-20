package zavgod.tech.Tech.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import zavgod.tech.Tech.models.Person;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PeopleRepositoryUnitTest {
    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    void findById_should_return_person() {
        Optional<Person> personOptional = peopleRepository.findById(1L);

        assertTrue(personOptional.isPresent());
    }

}