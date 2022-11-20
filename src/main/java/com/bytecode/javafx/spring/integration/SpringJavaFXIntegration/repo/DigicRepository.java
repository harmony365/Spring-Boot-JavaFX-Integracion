package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DigicRepository extends JpaRepository<Digic, String> {

    @Modifying
    @Transactional
    //@Query(value = "delete from digic d WHERE d.uuidProceso = :uuidProceso")
    void deleteAllByuuidProceso(String uuidProceso);

    @Modifying
    @Transactional
    @Query(value = "delete from digic d WHERE d.estatus_upload = :estatus")
    void deleteAllByestatusUpload(Integer estatus);

    @Query(value = "UPDATE digic d SET estatus_upload = 1, fecha_upload=CURRENT_TIMESTAMP WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso")
    List<Digic> updateStatusByValorDocumento(String valorDocumento,String uuidProceso);

    @Query(value = "UPDATE digic d SET estatus_upload = 1, fecha_upload=CURRENT_TIMESTAMP WHERE d.uuidProceso = :uuidProceso")
    List<Digic> updateStatusByuuidProceso(String uuidProceso);

    @Query(value = "SELECT d FROM digic d WHERE d.justificante = :justificante")
    List<Digic> findByJustificante(String justificante);

    @Query(value = "SELECT d FROM digic d WHERE d.justificante = :justificante AND d.uuidProceso = :uuidProceso")
    List<Digic> findByJustificanteuuidProceso(String justificante,String uuidProceso);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.estatus_upload = :estatus")
    List<Digic> findAllByValorDocumentoEstatus(String valorDocumento, Integer estatus);

    @Query(value = "SELECT d FROM digic d WHERE d.uuidProceso = :uuidProceso AND d.estatus_upload = :estatus")
    List<Digic> findAllByuuidProcesoEstatus(String uuidProceso, Integer estatus);

    @Query(value = "SELECT d.justificante, d.totalDigic FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso")
    List<Digic> findByValorDocumento(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT d FROM digic d WHERE d.uuidProceso = :uuidProceso")
    List<Digic> findByuuidProceso(String uuidProceso) ;

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
    List<Digic> findGroupByuuidProceso1(String uuidProceso, String valorMedioPago) ;

    @Query(value = "SELECT d from digic d" +
            "    WHERE d.cuentaSinIBAN = 'SI' AND d.uuidProceso = :uuidProceso AND d.valorMedioPago = :valorMedioPago" +
            "    group by d.valorMedioPago")
    List<Digic> findGroupByuuidProceso(String uuidProceso, String valorMedioPago) ;

    @Query(value = "SELECT DISTINCT trim(d.fechaLimiteSalida) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.fechaLimiteSalida)) > 0 ")
    List<Digic> findAllFechaLimiteSalida(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.email) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.email)) > 0 ")
    List<Digic> findAllEmail(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.descInstFinanciera) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.descInstFinanciera)) > 0 ")
    List<Digic> findAllDescInstFinanciera(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.identificadorBillete) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.identificadorBillete)) > 0 ")
    List<Digic> findAllIdentificadorBillete(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.modoPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.modoPago)) > 0 ")
    List<Digic> findAllModoPago(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.modoTransporte) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.modoTransporte)) > 0 ")
    List<Digic> findAllModoTransporte(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.paisBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.paisBanco)) > 0 ")
    List<Digic> findAllPaisBanco(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.valorMedioPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.valorMedioPago)) > 0  AND d.cuentaSinIBAN = 'SI'")
    List<Digic> findAllValorMedioPagoSI(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.valorMedioPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.valorMedioPago)) > 0 AND d.cuentaSinIBAN = 'NO'")
    List<Digic> findAllValorMedioPagoNO(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.claveBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.claveBanco)) > 0 ")
    List<Digic> findAllClaveBanco(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.claveControl) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.claveControl)) > 0 ")
    List<Digic> findAllClaveControl(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.codigoBic) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.codigoBic)) > 0 ")
    List<Digic> findAllCodigoBic(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.cuentaInternacional) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.cuentaInternacional)) > 0 ")
    List<Digic> findAllCuentaInternacional(String valorDocumento,String uuidProceso);
   
    @Query(value = "SELECT DISTINCT trim(d.cuentaSinIBAN) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.cuentaSinIBAN)) > 0 ")
    List<Digic> findAllCuentaSinIban(String valorDocumento,String uuidProceso);

    @Query(value = "SELECT DISTINCT trim(d.numeroABA) FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.uuidProceso = :uuidProceso AND length(trim(d.numeroABA)) > 0 ")
    List<Digic> findAllNumeroAba(String valorDocumento,String uuidProceso);


}
