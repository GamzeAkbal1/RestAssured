package apiExtractXml;

import com.jayway.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.given;
import static org.codehaus.groovy.tools.shell.util.Logger.io;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static io.restassured.path.xml.XmlPath.*;
import static org.hamcrest.Matchers.is;

/**
 * Created by xernea on 13.09.2017.
 */
public class extractXmltest {

    @BeforeClass
    public static void Init()
    {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
    }

    @Test
    @Ignore
    public void getCount(){

       String response = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","xml")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql")
                .then().extract().path("query.@yahoo:count");

        String value = response;
        Assert.assertThat("2",equalToIgnoringCase(value));

    }

    @Test
    public void getLang(){

        String lang = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","xml")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.@yahoo:lang");

        Assert.assertThat("en-US",equalToIgnoringCase(lang));

    }

    @Test
    public void getNameFromFirst()
    {
        String name = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","xml")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.results.rate[0].Name");

        Assert.assertThat("EUR/USD",equalToIgnoringCase(name));

    }

    @Test
    public void getAllRates(){

        String rates = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","xml")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").andReturn().prettyPrint();

    }

    @Test
    public void getAllRatesW(){

        String rates = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","xml")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").andReturn().asString();

        String valuesUnderRate = with(rates).get("query.results.rate").toString();

        System.out.println(valuesUnderRate);

    }

    @Test
    public void getSize()
    {
        int size = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","xml")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.results.rate.size()");

        Assert.assertThat(2,is(size));
    }


}
