package fileDownload;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

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

import java.io.File;
import java.util.regex.Matcher;

/**
 * Created by xernea on 19.09.2017.
 */
public class fileDownloadTest {

    //File inputFile = new File(System.getProperty("usr.dir")+File.separator+"geckodriver-v0.19.0-linux64.tar.gz");
    //int expectedSize = (int)inputFile.length();

    @Test
    public void verifyDownload(){

        File inputFile = new File(System.getProperty("user.dir")+File.separator+"geckodriver-v0.19.0-linux64.tar.gz");
        int expectedSize = (int)inputFile.length();
        System.out.println(expectedSize);

       byte [] file = given().when()
                    .get("https://github.com/mozilla/geckodriver/releases/download/v0.19.0/geckodriver-v0.19.0-linux64.tar.gz")
                    .then().extract().asByteArray();

        Assert.assertThat(expectedSize, new IsEqual(file.length));



    }



}
