package org.albianj.config.entity;

import java.util.HashMap;
import java.util.Map;

import org.albianj.verify.Validate;

public class ConfigItemCached
{
	private static Map<String,Element> map = new HashMap<String, Element>();
	
	public static Element get(String key)
	{
		if(Validate.isNullOrEmptyOrAllSpace(key)) return null;
		return map.get(key);
	}
	
	public synchronized static void Save(String key,Element elt)
	{
		if(Validate.isNullOrEmptyOrAllSpace(key)) return;
		if(map.containsKey(key))
		{
			Element oldElt = map.get(key);
			if(null == oldElt) 
			{
				map.put(key, elt);
			}
			else
			{
				oldElt.setDescription(elt.getDescription());
				oldElt.setEnable(elt.getEnable());
				oldElt.setValue(elt.getValue());
	//			oldElt.setForefatherKey(elt.getForefatherName());
	//			oldElt.setName(elt.getName());
			}
		}
		else
		{
			map.put(key, elt);
		}
	}
	
	public synchronized static void remove(String key)
	{
		if(Validate.isNullOrEmptyOrAllSpace(key)) return;
		map.remove(key);
	}
}
