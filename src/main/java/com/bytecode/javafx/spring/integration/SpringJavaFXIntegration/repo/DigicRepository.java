package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface DigicRepository extends JpaRepository<Digic, String> {

    @Query(value = "SELECT d FROM digic d WHERE d.justificante = :justificante")
    public List<Digic> findByJustificante(String justificante);

    @Query(value = "SELECT DISTINCT trim(d.email) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.email)) > 0 ")
    public List<Digic> findAllEmail(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.descInstFinanciera) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.descInstFinanciera)) > 0 ")
    public List<Digic> findAllDescInstFinanciera(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.identificadorBillete) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.identificadorBillete)) > 0 ")
    public List<Digic> findAllIdentificadorBillete(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.modoPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.modoPago)) > 0 ")
    public List<Digic> findAllModoPago(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.modoTransporte) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.modoTransporte)) > 0 ")
    public List<Digic> findAllModoTransporte(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.paisBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.paisBanco)) > 0 ")
    public List<Digic> findAllPaisBanco(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.valorMedioPago) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.valorMedioPago)) > 0 ")
    public List<Digic> findAllValorMedioPago(String valorDocumento);
    
    @Query(value = "SELECT DISTINCT trim(d.claveBanco) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.claveBanco)) > 0 ")
    public List<Digic> findAllClaveBanco(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.claveControl) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.claveControl)) > 0 ")
    public List<Digic> findAllClaveControl(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.codigoBic) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.codigoBic)) > 0 ")
    public List<Digic> findAllCodigoBic(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.cuentaInternacional) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.cuentaInternacional)) > 0 ")
    public List<Digic> findAllCuentaInternacional(String valorDocumento);
   
    @Query(value = "SELECT DISTINCT trim(d.cuentaSinIBAN) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.cuentaSinIBAN)) > 0 ")
    public List<Digic> findAllCuentaSinIban(String valorDocumento);

    @Query(value = "SELECT DISTINCT trim(d.numeroABA) FROM digic d WHERE d.valorDocumento = :valorDocumento AND length(trim(d.numeroABA)) > 0 ")
    public List<Digic> findAllNumeroAba(String valorDocumento);


}
