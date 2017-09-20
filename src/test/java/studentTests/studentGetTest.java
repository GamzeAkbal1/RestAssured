package studentTests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static javax.swing.UIManager.get;

/**
 * Created by xernea on 31.08.2017.
 */
public class studentGetTest {

    @BeforeClass
    public static void init()
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
       // RestAssured.basePath="/student";

    }

    @Test
    public void getStudentLists()
    {
        Response response =  given()
        .when()
        .get("/list");
        response.then()
        .statusCode(200);
        System.out.println(response.body().asString());

    }

    @Test
    public void getStudentInfo()
    {   int id = 1;
        Response response = given()
                .when()
                .get("/student/{id}",id);
                //.get("/1");
                response.then()
                        .statusCode(200);


    }

    @Test
    public void getStudentProgrammeZa()
    {
        Response response = given()
                .when()
                .get("/list?programme=Za");
        System.out.println(response.body().prettyPrint());

    }
}
