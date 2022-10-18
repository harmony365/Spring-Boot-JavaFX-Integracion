package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigicModoPagoRepository extends JpaRepository<DigicModoPago, String> {

    @Query(value = "SELECT d FROM digicmodopago d WHERE d.valorDocumento = :valorDocumento")
    public List<DigicModoPago> findByValorDocumento(String valorDocumento);

    @Modifying
    @Query(value = "UPDATE digicmodopago d SET estatusupload = 1, fechaupload = CURRENT_TIMESTAMP WHERE d.valorDocumento = :valorDocumento")
    List<Digic> updateStatusByValorDocumento(String valorDocumento);



}
