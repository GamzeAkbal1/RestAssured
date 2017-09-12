package studentTests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import studentBaseTest.baseTest;
import studentModel.Student;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by xernea on 05.09.2es017.
 */
public class studentPutTest extends baseTest {

    @BeforeClass
    public static void Init()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8080;
        RestAssured.basePath="/student";
    }

    @Test
    public void updateStudent()
    {
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Math");
        courses.add("Data Mining");
        Student student = new Student();
        student.setFirstName("Melodi");
        student.setLastName("Akbal");
        student.setEmail("gakbal112@gmail.com");
        student.setProgramme("stilTornacı");
        student.setCourses(courses);
        given().contentType(ContentType.JSON).when().body(student).put ("/101").then().statusCode(200);
    }
}
