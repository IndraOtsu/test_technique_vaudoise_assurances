package com.marcos.vaudoise.model.Contract;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.marcos.vaudoise.model.client.Client;
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
@Table(name = "contract")
@Entity
public class Contract {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "default gen_random_uuid()")
    private UUID id;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "update_date", nullable = false)
    private String updateDate;

    @Column(name = "end_date", nullable = true)
    private String endDate;

    @Column(name = "cost_amount", nullable = false)
    private String costAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = true)
    private Client client;

}
