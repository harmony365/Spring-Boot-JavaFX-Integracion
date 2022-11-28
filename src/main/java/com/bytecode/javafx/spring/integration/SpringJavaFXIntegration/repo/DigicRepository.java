package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DigicRepository extends JpaRepository<Digic, String> {

    @Modifying
    @Transactional
    //@Query(value = "delete from digic d WHERE d.uuidProceso = :uuidProceso")
    void deleteAllByuuidProceso(@Param("uuidProceso") String uuidProceso);

    @Modifying
    @Transactional
    @Query(value = "delete from digic d WHERE d.estatus_upload = :estatus")
    void deleteAllByestatusUpload(@Param("estatus") Integer estatus);

    @Modifying
    @Transactional
    @Query(value = "UPDATE digic d SET estatus_upload = 1, " +
                   "fecha_upload=CURRENT_TIMESTAMP WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso")
    List<Digic> updateStatusByValorDocumento(@Param("valorDocumento") String valorDocumento,@Param("uuidProceso") String uuidProceso);

    @Modifying
    @Transactional
    @Query(value = "UPDATE digic d SET estatus_upload = 1, fecha_upload=CURRENT_TIMESTAMP WHERE d.uuidProceso = :uuidProceso")
    List<Digic> updateStatusByuuidProceso(@Param("uuidProceso") String uuidProceso);

    @Modifying
    @Transactional
    @Query(value = "UPDATE digic d SET d.estatus_upload = :estatus WHERE d.justificante = :justificante")
    void updateEstatusUploadByJustificante(@Param("justificante") String justificante, @Param("estatus") String estatus);

    @Query(value = "SELECT d FROM digic d WHERE d.justificante = :justificante")
    List<Digic> findByJustificante(@Param("justificante") String justificante);

    @Query(value = "SELECT d FROM digic d WHERE d.justificante = :justificante AND d.uuidProceso = :uuidProceso ")
    List<Digic> findByJustificanteuuidProceso(@Param("justificante") String justificante, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.estatus_upload = :estatus")
    List<Digic> findAllByValorDocumentoEstatus(@Param("valorDocumento") String valorDocumento, @Param("estatus") Integer estatus);

    @Query(value = "SELECT d FROM digic d WHERE d.uuidProceso = :uuidProceso AND d.estatus_upload = :estatus")
    List<Digic> findAllByuuidProcesoEstatus(@Param("uuidProceso") String uuidProceso, @Param("estatus") Integer estatus);

    @Query(value = "SELECT d.justificante, d.totalDigic FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso")
    List<Digic> findByValorDocumento(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT d FROM digic d WHERE d.uuidProceso = :uuidProceso")
    List<Digic> findByuuidProceso(@Param("uuidProceso") String uuidProceso) ;

    @Query(value = "SELECT distinct" +
            "    d.paisBanco," +
            "    d.claveBanco," +
            "    d.claveControl," +
            "    d.codigoBic," +
            "    d.cuentaInternacional," +
            "    d.cuentaSinIBAN," +
            "    d.descInstFinanciera," +
            "    d.valorMedioPago from digic d" +
            "    WHERE d.cuentaSinIBAN = 'SI' AND d.uuidProceso = :uuidProceso AND d.valorMedioPago = :valorMedioPago" +
            "    group by d.valorMedioPago")
    List<Digic> findGroupByuuidProceso1(@Param("uuidProceso") String uuidProceso, @Param("valorMedioPago") String valorMedioPago) ;

    @Query(value = "SELECT d from digic d" +
            "    WHERE d.cuentaSinIBAN = 'SI' AND d.uuidProceso = :uuidProceso AND d.valorMedioPago = :valorMedioPago" +
            "    group by d.valorMedioPago")
    List<Digic> findGroupByuuidProceso(@Param("uuidProceso") String uuidProceso, @Param("valorMedioPago") String valorMedioPago) ;

    @Query(value = "SELECT DISTINCT trim(d.fechaLimiteSalida) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.fechaLimiteSalida)) > 0 ")
    List<Digic> findAllFechaLimiteSalida(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.email) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.email)) > 0 ")
    List<Digic> findAllEmail(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.descInstFinanciera) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.descInstFinanciera)) > 0 ")
    List<Digic> findAllDescInstFinanciera(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.identificadorBillete) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.identificadorBillete)) > 0 ")
    List<Digic> findAllIdentificadorBillete(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.modoPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.modoPago)) > 0 ")
    List<Digic> findAllModoPago(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.modoTransporte) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.modoTransporte)) > 0 ")
    List<Digic> findAllModoTransporte(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.paisBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.paisBanco)) > 0 ")
    List<Digic> findAllPaisBanco(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.valorMedioPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.valorMedioPago)) > 0  AND d.cuentaSinIBAN = 'SI'")
    List<Digic> findAllValorMedioPagoSI(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.valorMedioPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.valorMedioPago)) > 0 AND d.cuentaSinIBAN = 'NO'")
    List<Digic> findAllValorMedioPagoNO(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.claveBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.claveBanco)) > 0 ")
    List<Digic> findAllClaveBanco(@Param("valorDocumento")  String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.claveControl) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.claveControl)) > 0 ")
    List<Digic> findAllClaveControl(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.codigoBic) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.codigoBic)) > 0 ")
    List<Digic> findAllCodigoBic(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.cuentaInternacional) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.cuentaInternacional)) > 0 ")
    List<Digic> findAllCuentaInternacional(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.cuentaSinIBAN) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.cuentaSinIBAN)) > 0 ")
    List<Digic> findAllCuentaSinIban(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.numeroABA) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.numeroABA)) > 0 ")
    List<Digic> findAllNumeroAba(@Param("valorDocumento") String valorDocumento, @Param("uuidProceso") String uuidProceso);

}
