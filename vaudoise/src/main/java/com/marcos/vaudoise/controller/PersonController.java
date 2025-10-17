package com.marcos.vaudoise.controller;

import com.marcos.vaudoise.model.person.Person;
import com.marcos.vaudoise.model.person.PersonDTO;
import com.marcos.vaudoise.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all people", description = "Retrieves a list of all people in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of people"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "", produces = "application/json")
    public List<Person> getAll() {
        log.info("Requested get all people route");
        return personService.getAll();
    }

    @Operation(summary = "Get person by ID", description = "Retrieves a specific person by their unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved person"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Person> getById(@PathVariable UUID id){
        log.info("Requested get person by ID route");
        return personService.getById(id);
    }

    @Operation(summary = "Create a new person", description = "Creates a new person with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created person"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Person create(@RequestBody PersonDTO person) {
        log.info("Requested create person route");
        return personService.create(person);
    }

    @Operation(summary = "Delete person by ID", description = "Deletes a specific person by their unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted person"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public String delete(@PathVariable UUID id) {
        log.info("Requested delete person route");
        return personService.delete(id);
    }

    @Operation(summary = "Update person by ID", description = "Updates a specific person by their unique identifier with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated person"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Person update(@PathVariable UUID id, @RequestBody PersonDTO person) {
        log.info("Requested update person route");
        return personService.update(id, person);
    }

}
