package com.example.helpdesk.repository;

import com.example.helpdesk.dto.response.CustomerRespDto;
import com.example.helpdesk.model.CustomerPlan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerPlanRepository extends JpaRepository<CustomerPlan,Long> {
    @Query("""
 SELECT new com.example.helpdesk.dto.response.CustomerRespDto(
        cp.id,
        cp.activationDate,
        cp.endDate,
        c.name,
        p.planType
    )
    FROM CustomerPlan cp
    JOIN cp.customer c
    JOIN cp.plan p
    JOIN c.user u
    WHERE u.username = ?1
""")
    List<CustomerRespDto> getCustomerPlansByCustomerUsername(String customerUsername, Pageable pageable);
}
