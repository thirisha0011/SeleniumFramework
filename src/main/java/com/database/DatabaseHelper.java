package com.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.annotations.Test;

import com.excelSheet.DataProviders;

public class DatabaseHelper extends DataProviders{
	
	private Properties properties;
	private FileReader fileReader;
	private Connection con;
	private Statement createStatement;
	private DatabaseHelper helper;
	//Add Multiple Lists If u want to store data
	private List<String> datas = new ArrayList<>();
	private Map<String, List<String>> map = new HashMap<>();
	
	private DatabaseHelper() {}
	
	public synchronized DatabaseHelper getDatabaseHelper() {
		if(helper==null) 
			helper = new DatabaseHelper();
		return helper;
	}
	
	public Properties readProperties(String FilePath) throws IOException {
		fileReader = new FileReader(FilePath);
		properties = new Properties();
		properties.load(fileReader);
		return properties;
	}
	
	private void connectToDatabase() throws ClassNotFoundException, SQLException, IOException {
		String propertyFile = getData("DB Properties Path");
		Properties readProperties = readProperties(propertyFile);
		Class.forName(readProperties.getProperty("loadClass"));
		String dbURL = readProperties.getProperty("databaseURL");
		String host = readProperties.getProperty("host");
		String user = readProperties.getProperty("user");
		String password = readProperties.getProperty("password");
		String dbname = readProperties.getProperty("dbname");
		String portnumber = readProperties.getProperty("portnumber");
		
		System.out.println("URL is: "+dbURL+host+":"+portnumber+"/"+dbname+","+user+","+password);
		
		//con = DriverManager.getConnection("jdbc:mysql://"+host+":"+portnumber+"/"+dbname+","+user+","+password);
		//createStatement = con.createStatement();
	}
	
	public Map<String, List<String>> readDatas(String Query, String ColumnName) throws SQLException, ClassNotFoundException, IOException {
		connectToDatabase();
		ResultSet executeQuery = createStatement.executeQuery(Query);
		while(executeQuery.next()) {
			//Add Multiple executeQuery.getString(ColumnName); if u want
			String data = executeQuery.getString(ColumnName);
			//Add Multiple Lists If u want to store data
			datas.add(data);
		}
		map.put(ColumnName, datas);
		return map;
	}
	
	public String readData(String Query, String ColumnName) throws SQLException, ClassNotFoundException, IOException {
		connectToDatabase();
		ResultSet executeQuery = createStatement.executeQuery(Query);
		while(executeQuery.next()) {
			String data = executeQuery.getString(ColumnName);
			datas.add(data);
		}
		return ColumnName;
	}
}