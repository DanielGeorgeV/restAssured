package main.java.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Currencies {

    public String code;
    public String name;
    public String symbol;

    Currencies(JSONObject jsonObject){
        try {
            this.code = jsonObject.getString("code");
            this.name = jsonObject.getString("name");
            this.symbol = jsonObject.getString("symbol");
        }
        catch(JSONException e){}
    }

}
