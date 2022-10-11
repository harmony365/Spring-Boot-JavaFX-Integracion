package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WindowsUtils {
  private WindowsUtils() {}
  
  public static boolean isProcessRunning(String processName) throws IOException {
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    List<String> command = new ArrayList<String>();
    command.add("WMIC");
    command.add("process");
    command.add("list");
    command.add("brief");

    try {
      ProcessBuilder builder = new ProcessBuilder(command);
      Process process = builder.start();
      is = process.getInputStream();
      isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
      String line;
      processName = processName.toUpperCase();
      while ((line = br.readLine()) != null) {
        if (line.toUpperCase().indexOf(processName) > -1) return true;
      }
      return false;
    }
    finally {
      if (br != null) br.close();
      if (isr != null) isr.close();
      if (is != null) is.close();
    }
  }

  public static void main(String[] args) throws IOException {
    System.out.println(WindowsUtils.isProcessRunning("excel.exe"));
  }
}