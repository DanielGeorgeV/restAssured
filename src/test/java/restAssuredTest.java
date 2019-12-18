package test.java;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.java.Validator.TeamzzValidator;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;

public class restAssuredTest {

    TeamzzValidator validator = new TeamzzValidator();

    @BeforeTest
    public void beforeTest() {
        RestAssured.baseURI = "https://restcountries.eu/rest/v2/";
    }

    @Test
    public void positiveCase() {
        Response response = get("all?fields=name;capital;currencies;latlng");
        //get("all?fields=name;capital;currencies;latlng").then().assertThat().statusCode(200).body(matchesJsonSchemaInClasspath("/src/test/resources/restAssuredsample.json"));
        get("all?fields=name;capital;currencies;latlng").then().assertThat().statusCode(200).body(containsString("currencies"),containsString("name"),
                containsString("capital"),containsString("latlng"),containsString("code"),containsString("symbol"));
        validator.setCountry(new JSONArray(response.getBody().asString()));
        get("capital/"+validator.getACapitalName("India")+"?fields=name;capital;currencies;latlng;regionBlocs").
                then().statusCode(200).
                body("currencies.code",
                        Matchers.arrayContaining(validator.getACodeName("India"))).
                body("currencies.name",Matchers.arrayContaining(validator.getACurrencyName("India"))).
                body("currencies.symbol",Matchers.arrayContaining(validator.getASymbol("India"))).
                body("name",Matchers.arrayContaining(validator.getAName("India"))).
                body("capital",Matchers.arrayContaining(validator.getACapitalName("India"))).
                body("Latlng",Matchers.arrayContaining(validator.getALatlng("India")));
    }

    @Test
    public void negativeCase() {
        Response response = get("all?fields=name;capital;currencies;latlng");
        //get("all?fields=name;capital;currencies;latlng").then().assertThat().statusCode(200).body(matchesJsonSchemaInClasspath("/src/test/resources/restAssuredsample.json"));
        get("all?fields=name;capital;currencies;latlng").then().assertThat().statusCode(200).body(containsString("currencies"),containsString("name"),
                containsString("capital"),containsString("latlng"),containsString("code"),containsString("symbol"));
        validator.setCountry(new JSONArray(response.getBody().asString()));
        get("capital/"+validator.getACapitalName("India")+"123456?fields=name;capital;currencies;latlng;regionBlocs").
                then().statusCode(404).
                body("status",
                        Matchers.equalTo(404)).
                body("message",Matchers.equalTo("Not Found"));
    }

}
