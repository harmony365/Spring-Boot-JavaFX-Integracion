package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RepositoryTest {

        @Autowired
        private DigicRepository digicRepository;

        @Test
        @Transactional
        public void testQueryMethodAuthor() {
            List<Digic> a = digicRepository.findByValorDocumento("44303145Q");
        }
}