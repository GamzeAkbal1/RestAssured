package responseSpec;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
//import io.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.RequestSpecBuilder;
//import io.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by xernea on 19.09.2017.
 */
public class responseSpecTest {

    static RequestSpecBuilder reqBuilder;
    static RequestSpecification reqSpec;
    static ResponseSpecBuilder respBuilder;
    static ResponseSpecification respSpec;
    static Map<String,Object> responseHeaders = new HashMap<String, Object>();
    @BeforeClass
    public static void Init(){

        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
        reqBuilder = new RequestSpecBuilder();
        respBuilder = new ResponseSpecBuilder();
        reqBuilder.addParam("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")");
        reqBuilder.addParam("env","store://datatables.org/alltableswithkeys");
        reqBuilder.addParam("format","json");
        reqSpec = reqBuilder.build();
        respSpec = respBuilder.build();
        responseHeaders.put("Content-Type","application/json;charset=utf-8");
        responseHeaders.put("Server","ATS");



    }

    @Test
    public void checkCount(){

        given().spec(reqSpec)
                .when().get("/yql")
                .then()
                .log().headers()
                .spec(respSpec.expect().body("query.count",equalTo(2)))
                .spec(respSpec.statusCode(200));



                //spec(respSpec).log().body().statusCode(200);

    }

    @Test
    public void checkHeaders(){

        given().spec(reqSpec).when().get("/yql").then()
                .spec(respSpec.expect().headers(responseHeaders));


    }
}
