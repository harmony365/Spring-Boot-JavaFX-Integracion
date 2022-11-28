package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DigicModoPagoRepository extends JpaRepository<DigicModoPago, String> {

    @Modifying
    @Transactional
    //@Query(value = "delete from digicmodopago d WHERE d.uuidProceso = :uuidProceso")
    void deleteAllByuuidProceso(@Param("uuidProceso") String uuidProceso);

    @Modifying
    @Transactional
    //@Query(value = "delete from digicmodopago d WHERE d.estatusUpload = :estatus")
    void deleteAllByestatusUpload(@Param("estatus") Integer estatus);

    @Query(value = "SELECT d FROM digicmodopago d WHERE d.valorDocumento = :valorDocumento")
    public List<DigicModoPago> findByValorDocumento(@Param("valorDocumento") String valorDocumento);

    @Query(value = "SELECT d FROM digicmodopago d WHERE d.uuidProceso = :uuidProceso")
    public List<DigicModoPago> findByUuidProceso(@Param("uuidProceso") String uuidProceso);

    @Modifying
    @Query(value = "UPDATE digicmodopago d SET estatusUpload = 1, fechaupload = CURRENT_TIMESTAMP WHERE d.valorDocumento = :valorDocumento")
    List<DigicModoPago> updateStatusByValorDocumento(@Param("valorDocumento") String valorDocumento);

    @Modifying
    @Query(value = "SELECT d FROM digicmodopago d WHERE d.valorDocumento = :valorDocumento AND d.estatusUpload = :estatus")
    List<DigicModoPago> findAllByValorDocumentoEstatus(@Param("valorDocumento") String valorDocumento, @Param("estatus") Integer estatus);

    @Modifying
    @Query(value = "SELECT d FROM digicmodopago d WHERE d.uuidProceso = :uuidProceso AND d.estatusUpload = :estatus")
    List<DigicModoPago> findAllByuuidProcesoEstatus(@Param("uuidProceso") String uuidProceso, @Param("estatus") Integer estatus);

    @Modifying
    @Query(value = "SELECT d FROM digicmodopago d WHERE d.estatusUpload = :estatus")
    List<DigicModoPago> findAllByEstatus(@Param("estatus") Integer estatus);


}
