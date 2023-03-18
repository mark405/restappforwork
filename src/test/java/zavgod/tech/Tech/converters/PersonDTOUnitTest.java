package zavgod.tech.Tech.converters;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import zavgod.tech.Tech.DTO.PersonDTO;
import zavgod.tech.Tech.models.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDTOUnitTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    void when_convert_person_entity_to_personDTO_thenCorrect() {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("mark");
        person.setLastName("last");
        person.setAge(2);

        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);

        assertEquals(personDTO.getFirstName(), personDTO.getFirstName());
        assertEquals(personDTO.getLastName(), personDTO.getLastName());
        assertEquals(personDTO.getAge(), personDTO.getAge());

    }
}
