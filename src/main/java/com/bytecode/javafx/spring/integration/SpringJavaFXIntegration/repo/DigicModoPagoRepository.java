package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DigicModoPagoRepository extends JpaRepository<DigicModoPago, String> {

    @Modifying
    @Transactional
    //@Query(value = "delete from digicmodopago d WHERE d.uuidProceso = :uuidProceso")
    void deleteAllByuuidProceso(String uuidProceso);

    @Modifying
    @Transactional
    //@Query(value = "delete from digicmodopago d WHERE d.estatusUpload = :estatus")
    void deleteAllByestatusUpload(Integer estatus);

    @Query(value = "SELECT d FROM digicmodopago d WHERE d.valorDocumento = :valorDocumento")
    public List<DigicModoPago> findByValorDocumento(String valorDocumento);

    @Query(value = "SELECT d FROM digicmodopago d WHERE d.uuidProceso = :uuidProceso")
    public List<DigicModoPago> findByUuidProceso(String uuidProceso);

    @Modifying
    @Query(value = "UPDATE digicmodopago d SET estatusUpload = 1, fechaupload = CURRENT_TIMESTAMP WHERE d.valorDocumento = :valorDocumento")
    List<DigicModoPago> updateStatusByValorDocumento(String valorDocumento);

    @Modifying
    @Query(value = "SELECT d FROM digicmodopago d WHERE d.valorDocumento = :valorDocumento AND d.estatusUpload = :estatus")
    List<DigicModoPago> findAllByValorDocumentoEstatus(String valorDocumento, Integer estatus);

    @Modifying
    @Query(value = "SELECT d FROM digicmodopago d WHERE d.uuidProceso = :uuidProceso AND d.estatusUpload = :estatus")
    List<DigicModoPago> findAllByuuidProcesoEstatus(String uuidProceso, Integer estatus);

    @Modifying
    @Query(value = "SELECT d FROM digicmodopago d WHERE d.estatusUpload = :estatus")
    List<DigicModoPago> findAllByEstatus(Integer estatus);


}
