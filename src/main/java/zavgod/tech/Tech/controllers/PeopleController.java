package zavgod.tech.Tech.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zavgod.tech.Tech.DTO.PersonDTO;
import zavgod.tech.Tech.models.Person;
import zavgod.tech.Tech.services.PeopleService;
import zavgod.tech.Tech.utils.PersonErrorResponse;
import zavgod.tech.Tech.utils.PersonNotFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

//    @GetMapping()
//    public List<PersonDTO> getAll() {
//        return peopleService.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
//    }

    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable("id") Long id) {

        Person person = peopleService.findOne(id);

        return convertToPersonDTO(person);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException personNotFoundException) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                personNotFoundException.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
