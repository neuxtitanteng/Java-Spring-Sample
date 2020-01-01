package com.neux.spring.jpa.repository;

import com.neux.spring.jpa.model.AgentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentDataRepository extends JpaRepository<AgentData,String> {
}
