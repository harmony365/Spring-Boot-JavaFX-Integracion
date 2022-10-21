package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;


import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringJavaFxIntegrationApplicationTests {

    @Autowired
    private ClienteRepository clienteRepository;
		/*
	@Test
	public void contextLoads() {
		Cliente cliente = new Cliente();
		setFromUI(cliente);

	}


    public void setFromUI(Cliente cliente) {
		System.out.println("\nData txtApellido: "+ cliente.getApellido());
		System.out.println("\nData txtNombre: "+ cliente.getNombre());
		System.out.println("\nData txtTelefono: "+ cliente.getTelefono());
    }

*/



}
