package com.utility;
 

import java.io.File;
import java.util.ArrayList;
import java.util.*;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class JVMReport {
	public static void generateReport(String jsonFile) {
		// TODO Auto-generated method stub
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports");
		
		Configuration config =new Configuration(file, "Swag Labs");
		config.addClassifications("OS","Windows");
		config.addClassifications("browser","chrome");
		config.addClassifications("browser version","13");
		List<String> json = new ArrayList<>();
		json.add(jsonFile);
		ReportBuilder builder = new ReportBuilder(json, config);
		builder.generateReports();
		

	}

}
