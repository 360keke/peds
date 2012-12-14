package cn.com.peds.common;

import java.util.Iterator;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.com.peds.derby.DataObject;
import cn.com.peds.derby.DataStore;

public class JsonUtil {
	public static JSONArray DataSoreToJSONs(DataStore ds) {
		Vector v = ds.getResultRows();
		JSONArray arr = new JSONArray();
		JSONObject js = null;
		try {
			for (Iterator it = v.iterator(); it.hasNext();) {
				DataObject obj = (DataObject) it.next();
				String[] keys = obj.getAllKeys();
				js = new JSONObject();
				for (int i = 0; i < keys.length; i++) {
					String key = keys[i];
					Object value = obj.getObject(key);
					js.put(key, value == null ? "" : value.toString());
				}
				arr.put(js);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
}
