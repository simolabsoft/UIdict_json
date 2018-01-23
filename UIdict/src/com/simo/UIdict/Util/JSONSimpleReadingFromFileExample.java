package com.simo.UIdict.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONSimpleReadingFromFileExample {
	
	 public JSONSimpleReadingFromFileExample() {
			// TODO Auto-generated constructor stub
		}
		 public void readFromJsonFile(String path,Map<String, String> map)
		 {
			try
			{
				JSONParser parser = new JSONParser();
				 JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
			 

			        for (Object o : jsonArray) {
			            JSONObject wordObejct = (JSONObject) o;
			 
					String word = (String) wordObejct.get("word");
				    String traduction = (String) wordObejct.get("traduction");
				    map.put(word, traduction);
				}
			}catch (FileNotFoundException e) {
				   e.printStackTrace();
			  }
			catch (IOException e) {
				   e.printStackTrace();
				  }
			catch (ParseException e) {
				   e.printStackTrace();
				  }
			
		 }
		 public void removeFromJson(String path ,String objectToRemove)
		 {
			
			 try
				{
				 JSONParser parser = new JSONParser();
				 JSONObject wordObejct = new JSONObject();
					 JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
				        for (Object o : jsonArray) {
				             wordObejct = (JSONObject) o;
				            String word = (String) wordObejct.get("word");
				        if(word.equals(objectToRemove))
				        {
				        	break;
				        }   	       
					}
				      jsonArray.remove(wordObejct);
				      File file = new File(path);
						FileWriter fileWriter = new FileWriter(file);
						fileWriter.write(jsonArray.toJSONString());
						fileWriter.flush();
						fileWriter.close();
				        
				}catch (FileNotFoundException e) {
					   e.printStackTrace();
				  }
				catch (IOException e) {
					   e.printStackTrace();
					  }
				catch (ParseException e) {
					   e.printStackTrace();
					  }
			 
		 }
		 public void writeToJsonFile(String path,JSONObject wordObject)
			{ 
			 JSONParser parser = new JSONParser();
				try{
				JSONArray jsonArray = (JSONArray)parser.parse(new FileReader(path));
				jsonArray.add(wordObject);
				File file = new File(path);
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(jsonArray.toJSONString());
				fileWriter.flush();
				fileWriter.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			catch (ParseException e)
			{
					   e.printStackTrace();
		    }
}
		 public boolean isWordExist(String path,String word)
		 {
			 JSONParser parser = new JSONParser();
			 boolean exist = false;
				try{
				JSONArray jsonArray = (JSONArray)parser.parse(new FileReader(path));
			    for(Object o : jsonArray)
			    {
			    	JSONObject wordObject = (JSONObject) o;
			    	if(wordObject.get("word").equals(word))
			    	{
			    		exist = true;
			    	}
			    }
			    
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			catch (ParseException e)
			{
					   e.printStackTrace();
		    }
				return exist;
		 }
}

