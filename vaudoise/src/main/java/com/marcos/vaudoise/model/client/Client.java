package com.marcos.vaudoise.model.client;

import com.marcos.vaudoise.model.company.CompanyDTO;
import com.marcos.vaudoise.model.person.PersonDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {

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

    public Client(PersonDTO personDTO) {
        this.name = personDTO.getName();
        this.phone = personDTO.getPhone();
        this.email = personDTO.getEmail();
    }

    public Client(CompanyDTO companyDTO) {
        this.name = companyDTO.getName();
        this.phone = companyDTO.getPhone();
        this.email = companyDTO.getEmail();
    }
}
