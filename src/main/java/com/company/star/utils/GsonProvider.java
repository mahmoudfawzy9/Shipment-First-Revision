package com.company.star.utils;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonProvider {
	private GsonProvider() {

	}

	public static Gson getGsonSnakeCase() {
		return getGson(true);
	}

	public static Gson getGson() {
		return getGson(false);
	}

	private static Gson getGson(boolean isSnakeCase) {
		GsonBuilder gBuilder = new GsonBuilder();
		gBuilder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src, Type arg1, JsonSerializationContext arg2) {
				return src == null ? null : new JsonPrimitive(src.getTime());
			}
		});

		gBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		});
		gBuilder.setExclusionStrategies(new MyExclusionStrategy());

		if (isSnakeCase) {
			gBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		}

		return gBuilder.create();
	}
}
