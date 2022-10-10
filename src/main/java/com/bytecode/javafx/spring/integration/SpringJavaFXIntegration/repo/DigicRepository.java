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

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllEmail(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllClaveBanco(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllClaveControl(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllCodigoBic(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllCuentaInternacional(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllCuentaSinIban(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllDescInstFinanciera(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllIdentificadorBillete(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllModoPago(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllModoTransporte(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllNumeroAba(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllPaisBanco(String valorDocumento);

    @Query(value = "SELECT d FROM digic d WHERE d.valorDocumento = :valorDocumento")
    public List<Digic> findAllValorMedioPago(String valorDocumento);


}
