package studentTests;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import studentBaseTest.baseTest;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by xernea on 05.09.2017.
 */
public class studentDeleteTest extends baseTest {



    @Test
    public void deleteStudent(){

        given().when().delete("/109").then().statusCode(204);
    }
}
