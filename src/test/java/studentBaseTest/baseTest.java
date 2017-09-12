package studentBaseTest;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by xernea on 05.09.2017.
 */
public class baseTest {
    @BeforeClass
    public static void Init()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8080;
        RestAssured.basePath="/student";
    }
}
