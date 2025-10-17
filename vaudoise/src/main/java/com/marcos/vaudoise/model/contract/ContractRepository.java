package com.marcos.vaudoise.model.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID>, JpaSpecificationExecutor<Contract> {
    @Query("SELECT SUM(c.costAmount) FROM Contract c WHERE c.client.id = :clientId AND (c.endDate IS NULL or c.endDate > CURRENT_DATE)")
    Float getTotalCostAmountByClientId(@Param("clientId") UUID clientId);
}
