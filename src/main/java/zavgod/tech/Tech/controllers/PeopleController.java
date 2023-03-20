package zavgod.tech.Tech.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zavgod.tech.Tech.DTO.PersonDTO;
import zavgod.tech.Tech.models.Person;
import zavgod.tech.Tech.services.PeopleService;
import zavgod.tech.Tech.exceptions.PersonErrorResponse;
import zavgod.tech.Tech.exceptions.PersonNotFoundException;

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

    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable("id") Long id) {

        return convertToPersonDTO(peopleService.findOne(id));
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
