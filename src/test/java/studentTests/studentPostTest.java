package studentTests;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.assertion.ResponseTimeMatcher;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static javax.swing.UIManager.get;
import org.junit.BeforeClass;
import org.junit.Test;
import com.jayway.restassured.http.ContentType;


import studentBaseTest.baseTest;
import studentModel.Student;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.ArrayList;

import static com.jayway.restassured.path.xml.XmlPath.given;

/**
 * Created by xernea on 31.08.2017.
 */
public class studentPostTest extends baseTest {
    public String APPLICATION_JSON_VALUE = "application/json";
    public String APPLICATION_JSON_UTF8_VALUE = APPLICATION_JSON_VALUE + ";charset=UTF-8";

    @BeforeClass
    public static void Init()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8080;
        RestAssured.basePath="/student";



    }

    @Test
    public void createNewStudent(){

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Math");
        courses.add("Data Mining");
        Student student = new Student();
        student.setFirstName("Gamze");
        student.setLastName("Akbal");
        student.setEmail("gakbal11222@gmail.com");
        student.setProgramme("Tornacı");
        student.setCourses(courses);





        given().contentType("application/json")
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);



//given().contentType(ContentType.JSON).when().body(student).post().then().statusCode(201);



    }
}
