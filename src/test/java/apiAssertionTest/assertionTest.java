package apiAssertionTest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.given;
import static org.codehaus.groovy.tools.shell.util.Logger.io;
import static io.restassured.path.xml.XmlPath.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by xernea on 14.09.2017.
 */
public class assertionTest {

    static HashMap<String,Object> params = new HashMap<String, Object>();

    @BeforeClass
    public static  void Init()
    {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
        params.put("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")");
        params.put("format","json");
        params.put("env","store://datatables.org/alltableswithkeys");

    }

    @Test
    public void checkCount()
    {
         given().parameters(params)
                .when().get("/yql")
                .then().body("query.count",is(2));
    }

    @Test
    public  void checkName()
    {
        given().parameters(params)
                .when().get("yql")
                .then().body("query.results.rate[1].Name",equalTo("GBP/USD"));

    }

    @Test
    public void checkNameIsExistInNameCollection(){

        given().parameters(params)
                .when().get("yql")
                .then().body("query.results.rate.Name",hasItem("EUR/USD"));
    }

    @Test
    public void checkMultipleName(){

        given().parameters(params)
                .when().get("yql")
                .then().body("query.results.rate.Name",hasItems("EUR/USD","GBP/USD"));
    }

    @Test
    public void checkCountGreater(){

        given().parameters(params)
                .when().get("/yql")
                .then().body("query.count",greaterThan(1));
    }

    @Test
    public  void checkCountLess(){

        given().parameters(params)
                .when().get("/yql")
                .then().body("query.count",lessThan(4));
    }
}
