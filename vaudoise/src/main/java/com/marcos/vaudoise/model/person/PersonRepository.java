package com.marcos.vaudoise.model.person;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    List<Person> getAllByName(String name);
}
