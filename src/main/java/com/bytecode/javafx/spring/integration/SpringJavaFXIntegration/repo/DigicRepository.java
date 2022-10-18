package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigicRepository extends JpaRepository<Digic, String> {

    @Query(value = "UPDATE digic d SET estatus_upload = 1, fecha_upload=CURRENT_TIMESTAMP WHERE d.valorDocumento = :valorDocumento")
    List<Digic> updateStatusByValorDocumento(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.justificante = :justificante")
    List<Digic> findByJustificante(String justificante);


    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento AND d.estatus_upload = :estatus")
    List<Digic> findAllByValorDocumentoEstatus(String valorDocumento, Integer estatus);

    @Query(value = "SELECT d.justificante, d.totalDigic FROM digic d WHERE d.valorDocumento = :valorDocumento")
    List<Digic> findByValorDocumento(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.fechaLimiteSalida) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.fechaLimiteSalida)) > 0 ")
    List<Digic> findAllFechaLimiteSalida(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.email) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.email)) > 0 ")
    List<Digic> findAllEmail(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.descInstFinanciera) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.descInstFinanciera)) > 0 ")
    List<Digic> findAllDescInstFinanciera(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.identificadorBillete) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.identificadorBillete)) > 0 ")
    List<Digic> findAllIdentificadorBillete(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.modoPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.modoPago)) > 0 ")
    List<Digic> findAllModoPago(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.modoTransporte) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.modoTransporte)) > 0 ")
    List<Digic> findAllModoTransporte(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.paisBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.paisBanco)) > 0 ")
    List<Digic> findAllPaisBanco(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.valorMedioPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.valorMedioPago)) > 0 ")
    List<Digic> findAllValorMedioPago(String valorDocumento);
    
    @Query(value = "SELECT DISTINCT trim(d.claveBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.claveBanco)) > 0 ")
    List<Digic> findAllClaveBanco(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.claveControl) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.claveControl)) > 0 ")
    List<Digic> findAllClaveControl(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.codigoBic) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.codigoBic)) > 0 ")
    List<Digic> findAllCodigoBic(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.cuentaInternacional) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.cuentaInternacional)) > 0 ")
    List<Digic> findAllCuentaInternacional(String valorDocumento);
   
    @Query(value = "SELECT DISTINCT trim(d.cuentaSinIBAN) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.cuentaSinIBAN)) > 0 ")
    List<Digic> findAllCuentaSinIban(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.numeroABA) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.numeroABA)) > 0 ")
    List<Digic> findAllNumeroAba(String valorDocumento);


}
