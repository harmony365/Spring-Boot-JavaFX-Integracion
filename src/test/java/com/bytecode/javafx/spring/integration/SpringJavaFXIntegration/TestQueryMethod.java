package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class TestQueryMethod {
 
    @Autowired
    private DigicRepository digicRepository;
 
//    @Test
//    @Transactional
//    public void testQueryMethodAuthor() {
//        List<Digic> a = digicRepository.findByJustificante("Thorben");
//
//       // assertEquals(2+1+4);
//    }
}