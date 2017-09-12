package studentLogging;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import studentBaseTest.baseTest;

import static com.jayway.restassured.RestAssured.given;

import studentModel.Student;

import java.util.ArrayList;

/**
 * Created by xernea on 06.09.2017.
 */
public class loggingTest extends baseTest {

    @Test
    public void getHeaders(){

        System.out.println("------------Headers------------");
        given().log().headers().when().get("/1").then().statusCode(200);
    }

    @Test
    public void getParameters(){

        System.out.println("--------------Parameters----------");
        given().param("programme","Computer Science").param("limit",1)
                .log().parameters().when().get("/list").then().statusCode(200);
    }

    @Test
    public void getBody(){

        System.out.println("-------------Body----------------");
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Math1");
        courses.add("Math2");
        Student student = new Student();
        student.setFirstName("Lolodi");
        student.setLastName("Lölödi");
        student.setEmail("komodi@gmail.com");
        student.setProgramme("Zalama");
        student.setCourses(courses);
        given().contentType(ContentType.JSON).log().body().when().body(student).post().then().statusCode(201);

    }

    @Test
    public void getAllInf(){

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Math3");
        courses.add("Math4");
        Student student = new Student();
        student.setFirstName("Gamze");
        student.setLastName("Akbal");
        student.setEmail("gakbal@gmail.com");
        student.setProgramme("noProZa");
        student.setCourses(courses);
        given().contentType(ContentType.JSON).log().all().when().body(student).post().then().statusCode(201);

    }
}
