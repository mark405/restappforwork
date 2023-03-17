package zavgod.tech.Tech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zavgod.tech.Tech.models.Person;
import zavgod.tech.Tech.repositories.PeopleRepository;
import zavgod.tech.Tech.utils.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(Long id) {
        return peopleRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person with id(" + id + ") not found "));
    }

    public Integer getAgeById(Long id) {
        Person person = findOne(id);
        return person.getAge();
    }
}
