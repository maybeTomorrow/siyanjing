package me.thinkjet.utils.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public final class JSONUtils {

	public static String getTextForElement(final String json,
			final String element) {
		JSONObject o = new JSONObject(json);
		return o.getString(element);
	}

	public static Map<String, Object> getTextForElements(final String json,
			final String element) {
		JSONObject o = new JSONObject(json);
		Map<String, Object> maps = new HashMap<String, Object>();
		JSONObject ob = o.getJSONObject(element);
		@SuppressWarnings("rawtypes")
		Set keys = ob.keySet();

		for (Object key : keys) {
			maps.put((String) key, o.getString((String) key));
		}
		return maps;

	}
}
