package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigicModoPagoService {

    @Autowired
    DigicModoPagoRepository digiModoPagoRepository;

    public DigicModoPagoService(){}


}
