package com.javatest.framework.commons.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


public class EnumDesiralizer<T extends Enum<T>> implements JsonDeserializer<T> {

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return Enum.valueOf((Class<T>) typeOfT, json.getAsString().toUpperCase());
    }
}
