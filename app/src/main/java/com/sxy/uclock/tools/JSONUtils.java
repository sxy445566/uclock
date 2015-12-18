package com.sxy.uclock.tools;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONUtils {
	
	/**
	 * @function 转换JSONArray为list
	 * @param list
	 * @param jsonArrayString
	 */
	public void jsonArrayConversionToList(List<Object> list, String jsonArrayString){
		//TODO 还没写好
	    
	}
	
	/**
	 * @function 转换JSONArray（字符串）为ArrayList<Map<String, String>>
	 * @param jsonArrayString JSONArray字符串
	 * @return 转换好的ArrayList<Map<String, String>>
	 * @warn 该方法存在bug！！！
	 */
	public static List<Map<String, String>> jsonArrayConversionToList(String jsonArrayString){
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			JsonReader jr = new JsonReader(new StringReader(jsonArrayString));
		    jr.beginArray();
		    while (jr.hasNext()) {
		    	jr.beginObject();  
	            while(jr.hasNext()){  
	                String tagName = jr.nextName(); 
	                String valueName = jr.nextString();
	                Map<String, String> map = new HashMap<String, String>();
	                map.put(tagName, valueName);
	                list.add(map);
	            }  
	            //遍历JSON对象结束  
	            jr.endObject();
			}
		    //遍历数组结束  
		    jr.endArray();
		    jr.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @function 转换JSON为ArrayList<Map<String, String>>
	 * @param jsonObject 需要转换的JSON对象
	 * @return 转换好的ArrayList<Map<String, String>>
	 */
	public static List<Map<String, String>> jsonArrayConversionToList(Object jsonObject){
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			if(jsonObject instanceof JSONArray){	//判断为JSONArray
		    	JSONArray jsonArray = (JSONArray)jsonObject;
		    	for (int i = 0; i < jsonArray.length(); i++) {
			    	JSONObject json = jsonArray.getJSONObject(i);
			    	Map<String, String> map = new HashMap<String, String>();
					Iterator<?> keys = json.keys();
					while(keys.hasNext()){
					    String key = (String)keys.next();
					    map.put(key, json.getString(key));
					    if( json.get(key) instanceof JSONObject ){

					    }
					}
					list.add(map);
				}
		    }else if(jsonObject instanceof JSONObject){	//判断为JSONObject
		    	Map<String, String> temp = new HashMap<String, String>();
		    	JSONObject json = (JSONObject)jsonObject;
		    	Iterator<?> keys = json.keys();
				while(keys.hasNext()){
				    String key = (String)keys.next();
				    temp.put(key, json.getString(key));
				    if( json.get(key) instanceof JSONObject ){

				    }
				}
				list.add(temp);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
