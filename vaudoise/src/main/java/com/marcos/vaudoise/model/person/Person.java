package com.marcos.vaudoise.model.person;

import com.marcos.vaudoise.util.StringUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "person")
@Entity
public class Person {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "default gen_random_uuid()")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date", nullable = false, updatable = false)
    private LocalDate birthDate;

    public Person(PersonDTO personDTO) {
        this.name = personDTO.getName();
        this.phone = personDTO.getPhone();
        this.email = personDTO.getEmail();
        this.birthDate = StringUtils.parseToDate(personDTO.getBirthDate());
    }
}
