package main.java.Validator;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.entity.Countries;
import java.util.HashMap;

public class TeamzzValidator {

    HashMap<String,Countries> countriesResponse = new HashMap<String,Countries>();

    public void setCountry(JSONArray jsonArray) {
        for (int i = 0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            countriesResponse.put(jsonObject.getString("name"),new Countries(jsonObject));
        }
    }

    public String getACapitalName(String country){
        return countriesResponse.get(country).capital;
    }

    public String getACodeName(String country){
        return countriesResponse.get(country).currencies.code;
    }

    public String getACurrencyName(String country){
        return countriesResponse.get(country).currencies.name;
    }

    public String getASymbol(String country){
        return countriesResponse.get(country).currencies.symbol;
    }

    public String getAName(String country){
        return countriesResponse.get(country).name;
    }

    public double[] getALatlng(String country){
        return countriesResponse.get(country).latlng;
    }
}