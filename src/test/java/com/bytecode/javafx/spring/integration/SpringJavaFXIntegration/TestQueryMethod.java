package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;

import javafx.application.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestQueryMethod {
 
    @Autowired
    private DigicRepository digicRepository;
 
    @Test
    @Transactional
    public void testQueryMethodAuthor() {
        List<Digic> a = digicRepository.findByJustificante("Thorben");

       // assertEquals(2+1+4);
    }
}