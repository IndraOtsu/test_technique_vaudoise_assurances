package com.marcos.vaudoise.model.company;

import com.marcos.vaudoise.model.person.PersonDTO;
import com.marcos.vaudoise.util.StringUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "company")
@Entity
public class Company {

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

    @Column(name = "company_identifier", nullable = false, updatable = false)
    private String companyIdentifier;

    public Company(CompanyDTO companyDTO) {
        this.name = companyDTO.getName();
        this.phone = companyDTO.getPhone();
        this.email = companyDTO.getEmail();
        this.companyIdentifier = companyDTO.getCompanyIdentifier();
    }
}
