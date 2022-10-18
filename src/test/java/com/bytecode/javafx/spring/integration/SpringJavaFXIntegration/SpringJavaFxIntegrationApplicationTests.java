package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;


import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringJavaFxIntegrationApplicationTests {

    @Autowired
    private ClienteRepository clienteRepository;
		
//	@Test
//	public void contextLoads() {
//		Cliente cliente = new Cliente();
//		setFromUI(cliente);
//		GetParametros();
//
//	}

/*
    public void setFromUI(Cliente cliente) {
		System.out.println("\nData txtApellido: "+ cliente.getApellido());
		System.out.println("\nData txtNombre: "+ cliente.getNombre());
		System.out.println("\nData txtTelefono: "+ cliente.getTelefono());
    }

    public void GetParametros(){
        // parsing file "Parametros.json"
        //Object ob;
        try {

            String command = "wmic csproduct get UUID";
            StringBuffer output = new StringBuffer();

            Process SerNumProcess = Runtime.getRuntime().exec(command);
            BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));

            String line;
            while ((line = sNumReader.readLine()) != null) {
                output.append(line + "\n");
            }
            String MachineID=output.toString().substring(output.indexOf("\n"), output.length()).trim();
            System.out.println(MachineID);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    */



}
