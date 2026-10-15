package com.example.helpdesk.repository;

import com.example.helpdesk.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends  JpaRepository<Plan,Long> {

}
