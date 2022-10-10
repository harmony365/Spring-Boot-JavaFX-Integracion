package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DigicRepository extends JpaRepository<Digic, String> {

    @Query(value = "SELECT d FROM digic d WHERE d.justificante = :justificante")
    public List<Digic> findByJustificante(String justificante);


}
