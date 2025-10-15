package com.marcos.vaudoise.model.company;

import com.marcos.vaudoise.model.client.Client;
import com.marcos.vaudoise.model.person.PersonDTO;
import com.marcos.vaudoise.util.StringUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "company")
@Entity
@PrimaryKeyJoinColumn(name = "client_id")
public class Company extends Client {

    @Column(name = "company_identifier", nullable = false, updatable = false)
    private String companyIdentifier;

    public Company(CompanyDTO companyDTO) {
        super(companyDTO);
        this.companyIdentifier = companyDTO.getCompanyIdentifier();
    }
}
