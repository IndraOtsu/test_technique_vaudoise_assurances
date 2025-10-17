package com.marcos.vaudoise.model.contract;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcos.vaudoise.model.client.Client;
import com.marcos.vaudoise.util.StringUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "contract")
@Entity
public class Contract {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "default gen_random_uuid()")
    private UUID id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @JsonIgnore
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    @Column(name = "cost_amount", nullable = false)
    private float costAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = true)
    private Client client;

    public Contract(ContractDTO contractDTO) {
        this.startDate = StringUtils.parseToLocalDateTime(contractDTO.getStartDate());
        this.endDate = contractDTO.getEndDate() != null ? StringUtils.parseToLocalDateTime(contractDTO.getEndDate()) : null;
        this.updateDate = LocalDateTime.now();
        this.costAmount = contractDTO.getCostAmount();
    }
}
