package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DigicModoPagoRepository extends JpaRepository<DigicModoPago, String> {

    @Query(value = "SELECT d FROM digicmodopago d WHERE d.valorDocumento = :valorDocumento")
    public List<DigicModoPago> findByValorDocumento(String valorDocumento);


}
