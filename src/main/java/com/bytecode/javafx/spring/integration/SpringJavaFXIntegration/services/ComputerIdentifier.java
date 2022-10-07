package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import javafx.stage.Stage;


public class ComputerIdentifier {

    static SystemInfo systemInfo = new SystemInfo();
    static OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
    static HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
    static CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
    static ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
    
    public static String generateLicenseKey(){

        String vendor = operatingSystem.getManufacturer();
        String processorSerialNumber = computerSystem.getSerialNumber();
        String processorIdentifier = centralProcessor.getProcessorIdentifier().toString();
        int processors = centralProcessor.getLogicalProcessorCount();

        String delimiter = "\n";

        return vendor +
                delimiter +
                processorSerialNumber +
                delimiter +
                processorIdentifier +
                delimiter +
                "CPU:" + processors;
    }

    public static String cpuUUI(){

        String processorSerialNumber = computerSystem.getSerialNumber();
        return processorSerialNumber ;
    }


    //@Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
    /* 
    public static void main(String[] arguments)
    {
        String identifier = generateLicenseKey();
        System.out.println(identifier);
    }
    */
}