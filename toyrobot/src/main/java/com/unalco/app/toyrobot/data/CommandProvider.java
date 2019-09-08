package com.unalco.app.toyrobot.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author yasinunal
 * This object retrieves all commands from a file and returns as a list.
 */
public class CommandProvider {
	
	public List<String> generate() {
		
		String filepath = "";
		InputStream inputStream = null;
		
		List<String> commandList = new ArrayList<String>();
		
		try {
			Properties prop = new Properties();
			String propFileName = "application.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
 
			filepath = prop.getProperty("toy.robot.commands.file");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			
			try {
				inputStream.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		Scanner scanner;
		
		try {
			
			scanner = new Scanner(new File(filepath));
			
			while (scanner.hasNextLine()) {
				
				String command = scanner.nextLine().toUpperCase();
				
				commandList.add(command);
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return commandList;
	}

}
