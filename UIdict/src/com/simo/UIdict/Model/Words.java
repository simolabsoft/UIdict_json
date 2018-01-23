package com.simo.UIdict.Model;

import java.util.HashMap;
import java.util.Map;

import com.simo.UIdict.Util.JSONSimpleReadingFromFileExample;

public class Words {
      private Map<String,String> map =new HashMap<String,String>();  
      private JSONSimpleReadingFromFileExample jsonreading = new JSONSimpleReadingFromFileExample();
public Words() {
	jsonreading.readFromJsonFile("Resources/dic.json",map);
}
public Map<String, String> getMap()
{
	return map;
}
public JSONSimpleReadingFromFileExample getjsonreading()
{
	return jsonreading;
}

	
}
