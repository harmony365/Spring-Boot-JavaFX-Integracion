package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class RepositoryTest {

        @Autowired
        private DigicRepository digicRepository;
/*
        @Test
        @Transactional
        public void testQueryMethodAuthor() {
            List<Digic> a = digicRepository.findByValorDocumento("44303145Q");
        }

 */
}