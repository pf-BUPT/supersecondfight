package jd.pengfeng.supersecondfight.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {
	final static Gson gson = new Gson();
	final static JsonParser jsonParser = new JsonParser();

	public static Gson instance() {
		return gson;
	}

	public static JsonElement parseJson(String str) {
		return jsonParser.parse(str);
	}

	public static JsonObject parseJsonObject(String str) {
		JsonElement jElement = jsonParser.parse(str);
		if (jElement instanceof JsonObject) {
			JsonObject jObject = jElement.getAsJsonObject();
			return jObject;
		}
		return null;
	}

	public static JsonArray parseJsonArray(String str) {
		JsonElement jElement = jsonParser.parse(str);
		if (jElement instanceof JsonArray) {
			JsonArray jArray = jElement.getAsJsonArray();
			return jArray;
		}
		return null;
	}
}
