package com.marcos.vaudoise.model.company;

import com.marcos.vaudoise.model.client.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
