package com.lior.coupons.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CacheController {

	private Map<String, Object> dataMap;
	private Map<Long, ArrayList<String>> tokenMap;

	public CacheController() {
		this.dataMap = new ConcurrentHashMap<String,Object>();
		this.tokenMap= new ConcurrentHashMap<Long, ArrayList<String>>();
	}

	public void putData(String key, Object value) {
		this.dataMap.put(key, value);
	}

	public Object getData(String key) {
		return this.dataMap.get(key);
	}
	
	public void deleteData(long key, String token) {
		ArrayList<String> value= this.tokenMap.get(key);
		if (value.size()==1) {
			this.tokenMap.remove(key);
		} else {
			value.remove(token);
			this.tokenMap.put(key, value);
		}
		this.dataMap.remove(token);
	}
	
	public void putToken(long key, String token) {
		ArrayList<String> value= this.tokenMap.get(key);
		if(value== null) {
			value= new ArrayList<String>();
		}
		value.add(token);
		this.tokenMap.put(key, value);
	}
	
	public void deleteAllUserData(long key) {
		ArrayList<String> tokens= this.tokenMap.get(key);
		this.tokenMap.remove(key);
		if (tokens != null) {
			for( String token: tokens) {
				this.dataMap.remove(token);
			}
		}
	}




}
