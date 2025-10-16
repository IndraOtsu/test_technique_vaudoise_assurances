package com.marcos.vaudoise.model.person;

import com.marcos.vaudoise.model.client.Client;
import com.marcos.vaudoise.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
@PrimaryKeyJoinColumn(name = "client_id")
public class Person extends Client {

    @Column(name = "birth_date", nullable = false, updatable = false)
    private LocalDate birthDate;

    public Person(PersonDTO personDTO) {
        super(personDTO);
        this.birthDate = StringUtils.parseToLocalDate(personDTO.getBirthDate());
    }
}
