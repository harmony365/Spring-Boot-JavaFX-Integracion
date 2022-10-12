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

    @Query(value = "SELECT DISTINCT d.email FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllEmail(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.descInstFinanciera FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllDescInstFinanciera(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.identificadorBillete FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllIdentificadorBillete(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.modoPago FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllModoPago(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.modoTransporte FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllModoTransporte(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.paisBanco FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllPaisBanco(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.valorMedioPago FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllValorMedioPago(String valorDocumento);
    
    @Query(value = "SELECT DISTINCT d.claveBanco FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllClaveBanco(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.claveControl FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllClaveControl(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.codigoBic FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllCodigoBic(String valorDocumento);

    @Query(value = "SELECT DISTINCT d.cuentaInternacional FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllCuentaInternacional(String valorDocumento);
   
    @Query(value = "SELECT d.cuentaSinIBAN FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllCuentaSinIban(String valorDocumento);

    @Query(value = "SELECT d.numeroABA FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllNumeroAba(String valorDocumento);


}
