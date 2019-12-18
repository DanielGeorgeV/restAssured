package main.java.entity;

import org.json.JSONException;
import org.json.JSONObject;


public class Countries {

    public Currencies currencies;
    public String name;
    public String capital;
    public double[] latlng = new double[2];

    public Countries(JSONObject jsonObject){
        try {
            this.currencies = new Currencies(jsonObject.getJSONArray("currencies").getJSONObject(0));
            this.name = jsonObject.getString("name");
            this.capital = jsonObject.getString("capital");
            this.latlng[0] = (Double) jsonObject.getJSONArray("latlng").get(0);
            this.latlng[1] = (Double) jsonObject.getJSONArray("latlng").get(1);
        }
        catch(JSONException e){}
    }

}
