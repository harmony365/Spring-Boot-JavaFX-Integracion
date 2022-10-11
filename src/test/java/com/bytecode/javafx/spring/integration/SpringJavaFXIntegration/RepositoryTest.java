package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private DigicRepository digicRepository;

    @Test
    void findDerByJustificante_OK() {

        String miJustificante = "40324000042450";

        Digic digitIn = new Digic();
        digitIn.setJustificante(miJustificante);
        digitIn.setApellidosViajero("Ramos");
        digicRepository.save(digitIn);
        
        List<Digic> digits = digicRepository.findByJustificante(miJustificante);
        Assert.assertEquals(digits.get(0).getApellidosViajero(), "Ramos");
    }







}
