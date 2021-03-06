package studentExtractJson;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
/**
 * Created by xernea on 06.09.2017.
 */
public class extractTest {

    @BeforeClass
    public static void Init(){

        RestAssured.baseURI="https://query.yahooapis.com";
        RestAssured.basePath="/v1/public";

    }

    @Test
    public void getCount()
    {
        int counter;
        counter = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair=\"USDINR\"")
                .param("format","json")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.count");
        System.out.println("Count = "+counter);

        System.out.println("================================================================================");
        Assert.assertThat(1,is(equalTo(counter)));
    }

    @Test
    public void getRateName() {

        String name;
        name = given().param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair=\"USDINR\"")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when().get("/yql").then().extract().path("query.results.rate.Name");

        //System.out.println("Extract rate = " + name);

        System.out.println("================================================================================");
        Assert.assertThat("eşit mi","USD/IoNR", is(not(name)));


    }

    @Test
    public void getRateSize(){

        int size;
        size = given().param("q","select * from yahoo.finance.xchange where pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("diagnostics","true")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.results.rate.size()");

        System.out.println("Size Of = " +size);

        System.out.println("================================================================================");

        Assert.assertThat(2,is(size));
    }

    @Test
    public void getAllNames(){

        List<String> nameList;
        nameList = given().param("q","select * from yahoo.finance.xchange where pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("diagnostics","true")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.results.rate.Name");

        System.out.println("----Name List----");

        for(int i = 0; i<nameList.size() ; i++)
        {
            if(i==nameList.size()-1)
                System.out.print(nameList.get(i));
            else
                System.out.print(nameList.get(i)+" - ");
        }
        System.out.println();

        System.out.println("================================================================================");

        Assert.assertThat("EUR/USD , GBP/USD",is(CoreMatchers.containsString(nameList.get(0)+" , "+nameList.get(1))));
    }

    @Test
    public void getValueWithANameBad()
    {
        List<String> values = given().param("q","select * from yahoo.finance.xchange where pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.results.rate.findAll{it.Name=='EUR/USD'}");

        System.out.println("================== All EUR/USD Values ==================");
        System.out.println(values);
        String[] valueList = values.toString().split(",");
        Assert.assertThat(" id=EURUSD", equalToIgnoringCase(valueList[3]));

    }
    @Test
    public void getValueWithANameGood()
    {
        List<String> values = given().param("q","select * from yahoo.finance.xchange where pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql").then().extract().path("query.results.rate.findAll{it.Name=='EUR/USD'}.Name");

        System.out.println("================== All EUR/USD Values ==================");
        System.out.println(values);

        Assert.assertThat("EUR/USD", equalToIgnoringCase(values.get(0)));

    }

    @Test
    public void getTheNamesGreaterThanTen(){

        Response response = given().param("q","select * from yahoo.finance.xchange where pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("env","store://datatables.org/alltableswithkeys")
                .param("diagnostics","true")
                .when().get("/yql");
        List<String> nameList = response.then().extract().path("query.results.rate.findAll{it.Rate>'1.3'}.Name");
        System.out.print(nameList);
        Assert.assertThat("GBP/USD",equalToIgnoringCase(nameList.get(0)));
    }

    @Test
    public void getRateWithSpecName()
    {
        Response response = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql");

        response.then().extract().path("query.results.rate.findAll{it.Name=='GBP/USD'}.Rate");
        System.out.println(response.prettyPrint());


    }

    @Test
    public void getValueStartsWithEur()
    {
        Response response = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql");
        List <String> values = response.then().extract().path("query.results.rate.findAll{it.id==~/EUR.*/}");
        System.out.println(values);
    }

    @Test
    public void getValueEndsWithSd()
    {
        Response response = given().param("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")")
                .param("format","json")
                .param("env","store://datatables.org/alltableswithkeys")
                .when().get("/yql");
        response.then().extract().path("query.results.rate.findAll{it.id==~/.*SD/}");
        System.out.println(response.prettyPrint());



    }

}
