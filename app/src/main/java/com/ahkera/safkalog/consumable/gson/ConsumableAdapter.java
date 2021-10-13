package com.ahkera.safkalog.consumable.gson;

import com.ahkera.safkalog.consumable.Consumable;

import com.ahkera.safkalog.consumable.Ingredient;
import com.ahkera.safkalog.consumable.Recipe;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Custom GSON TypeAdapter for Consumables.
 * @author Vilhelm
 */
public class ConsumableAdapter implements JsonSerializer<Consumable>, JsonDeserializer<Consumable> {

    /**
     * Serializes the consumable the right way, by adding the information about what type of
     * consumable the object actually is.
     * @param consumable The consumable that will be serialized
     * @param type Type
     * @param context Serialization context
     * @return Consumable as a JsonElement
     */
    @Override
    public JsonElement serialize(Consumable consumable, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(consumable.getClass().getSimpleName()));
        result.add("properties", context.serialize(consumable, consumable.getClass()));
        return result;
    }

    /**
     * Deserializes the JsonElement to Consumable
     * @param jsonElement The JsonElement that will be deserialized
     * @param type Type
     * @param context Serialization context
     * @return The consumable as a object
     * @throws JsonParseException parsing exception
     */
    @Override
    public Consumable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        String consumableType = json.get("type").getAsString();
        JsonElement properties = json.get("properties");

        switch(consumableType) {
            case "Ingredient":
                return context.deserialize(properties, Ingredient.class);

            case "Recipe":
                return context.deserialize(properties, Recipe.class);
        }

        return null;
    }
}
