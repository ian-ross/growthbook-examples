package com.growthbook.examples.acme.models;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class UserAttributes {
    public String id;

    public Boolean isEmployee;

    public Boolean isLoggedIn;

    public String country;

    public UserAttributes(String id, String country, Boolean isEmployee, Boolean isLoggedIn) {
        this.id = id;
        this.country = country;
        this.isEmployee = isEmployee;
        this.isLoggedIn = isLoggedIn;
    }

    @NonNull
    @Override
    public String toString() {
        return "User = (id =" + this.id + ", country = " + this.country + ", isEmployee = " + this.isEmployee + ", isLoggedIn = " + this.isLoggedIn + ")";
    }

    public static UserAttributes fromJson(String json) {
        try {
            JSONObject object = new JSONObject(json);

            String id = (String) object.get("id");
            String country = (String) object.get("country");
            Boolean isEmployee = (Boolean) object.get("employee");
            Boolean isLoggedIn = (Boolean) object.get("loggedIn");


            return new UserAttributes(
                id,
                country,
                isEmployee,
                isLoggedIn
            );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toJson() {
        JSONObject object = new JSONObject();
        try {
            object.put("country", this.country);
            object.put("id", this.id);
            object.put("employee", this.isEmployee);
            object.put("loggedIn", this.isLoggedIn);

            return object.toString(2);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
