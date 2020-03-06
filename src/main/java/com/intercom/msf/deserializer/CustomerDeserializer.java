package com.intercom.msf.deserializer;

import com.google.gson.*;
import com.intercom.msf.model.Coordinates;
import com.intercom.msf.model.Customer;

import java.lang.reflect.Type;

public class CustomerDeserializer implements JsonDeserializer<Customer> {

    @Override
    public Customer deserialize(JsonElement jElement, Type typeOfT, JsonDeserializationContext context)throws JsonParseException {
        JsonObject jsonObject = jElement.getAsJsonObject();
        int id = jsonObject.get("user_id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        double latitude = jsonObject.get("latitude").getAsDouble();
        double longitude = jsonObject.get("longitude").getAsDouble();
        Coordinates c = new Coordinates(latitude, longitude);
        return new Customer(id, name, c);
    }
}