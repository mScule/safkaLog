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

    @Override
    public JsonElement serialize(Consumable consumable, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(consumable.getClass().getSimpleName()));
        result.add("properties", context.serialize(consumable, consumable.getClass()));
        return result;
    }

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
