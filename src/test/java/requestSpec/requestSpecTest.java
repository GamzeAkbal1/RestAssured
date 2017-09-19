package requestSpec;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
//import io.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.RequestSpecBuilder;
//import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.given;
/**
 * Created by xernea on 18.09.2017.
 */
public class requestSpecTest {

    static RequestSpecBuilder builder;
    static RequestSpecification rspec;

    @BeforeClass
    public static void Init(){

        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
        builder = new RequestSpecBuilder();
        builder.addParam("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")");
        builder.addParam("env","store://datatables.org/alltableswithkeys");
        builder.addParam("format","json");
        rspec = builder.build();



    }

    @Test
    public void testOne(){

        given().spec((com.jayway.restassured.specification.RequestSpecification) rspec)
                .log().all()
                .when().get("/yql")
                .then().log().body().statusCode(200);

    }

}
