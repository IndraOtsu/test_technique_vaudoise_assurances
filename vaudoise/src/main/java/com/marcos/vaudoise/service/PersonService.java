package com.marcos.vaudoise.service;

import com.marcos.vaudoise.model.person.Person;
import com.marcos.vaudoise.model.person.PersonDTO;
import com.marcos.vaudoise.model.person.PersonRepository;
import com.marcos.vaudoise.util.StringUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CommonsLog
@Service
public class PersonService {

    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        log.info("Requested get all people service");
        return personRepository.findAll();
    }

    public Optional<Person> getById(UUID id) {
        log.info("Requested get person by id service");
        return personRepository.findById(id);
    }

    public Person create(PersonDTO person) {
        log.info("Requested create person service: " + person);
        if(!fieldValidator(person)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid field format");
        }
        Person newPerson = new Person(person);
        personRepository.save(newPerson);
        return newPerson;
    }

    public String delete(UUID id) {
        log.info("Requested delete person service");
        personRepository.deleteById(id);
        return "Person deleted successfully";
    }

    public Person update(UUID id, PersonDTO person) {
        log.info("Requested update person service");
        if(!fieldValidator(person)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid field format");
        }
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person updPerson = existingPerson.get();
            updPerson.setName(person.getName());
            updPerson.setEmail(person.getEmail());
            updPerson.setPhone(person.getPhone());
            personRepository.save(updPerson);
            return updPerson;
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Person not found");
        }
    }

    private boolean fieldValidator(PersonDTO person) {

        return (person.getBirthDate() == null || StringUtils.isValidISO8601Date(person.getBirthDate())) &&
                StringUtils.isValidEmail(person.getEmail()) &&
                StringUtils.isValidPhone(person.getPhone());
    }
}
