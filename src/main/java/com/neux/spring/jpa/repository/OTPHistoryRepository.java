package com.neux.spring.jpa.repository;

import com.neux.spring.jpa.model.OTPHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OTPHistoryRepository extends JpaRepository<OTPHistory,String> {


}
