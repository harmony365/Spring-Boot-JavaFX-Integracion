package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigicRepository extends JpaRepository<Digic, String> {

}
