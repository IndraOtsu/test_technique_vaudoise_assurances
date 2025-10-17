package com.marcos.vaudoise.model.contract;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class ContractSpecification {

    public static Specification<Contract> hasClientId(UUID clientId) {
        return (root, query, cb) ->
                cb.equal(root.get("client").get("id"), clientId);
    }

    public static Specification<Contract> isActive(boolean inactive) {
        return (root, query, cb) -> {
            if (!inactive) {
                // inactive = false => end_date > now
                return cb.greaterThan(root.get("endDate"), new Date());
            } else {
                return cb.or(
                        cb.lessThanOrEqualTo(root.get("endDate"), new Date()),
                        cb.isNull(root.get("endDate"))
                );
            }
        };
    }

    public static Specification<Contract> updatedAfter(LocalDateTime updateDate) {
        return (root, query, cb) ->
                cb.greaterThan(root.get("updateDate"), updateDate);
    }
}