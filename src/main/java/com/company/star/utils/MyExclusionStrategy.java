package com.company.star.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class MyExclusionStrategy implements ExclusionStrategy {

	private final Class<?> typeToSkip;

	public MyExclusionStrategy() {
		this.typeToSkip = null;
	}

	public MyExclusionStrategy(Class<?> typeToSkip) {
		this.typeToSkip = typeToSkip;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return (f.getAnnotation(ExcludeField.class) != null);
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return (clazz == typeToSkip);
	}

}
