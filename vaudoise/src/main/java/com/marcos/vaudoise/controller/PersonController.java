package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.model.person.Person;
import com.marcos.vaudoise.model.person.PersonDTO;
import com.marcos.vaudoise.service.PersonService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CommonsLog
@RestController
@RequestMapping(path = "/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Person> getAll() {
        log.info("Requested get all people route");
        return personService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Person> getById(@PathVariable UUID id){
        log.info("Requested get person by ID route");
        return personService.getById(id);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Person create(@RequestBody PersonDTO person) {
        log.info("Requested create person route");
        return personService.create(person);
    }

    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public String delete(@PathVariable UUID id) {
        log.info("Requested delete person route");
        return personService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Person update(@PathVariable UUID id, @RequestBody PersonDTO person) {
        log.info("Requested update person route");
        return personService.update(id, person);
    }

}
