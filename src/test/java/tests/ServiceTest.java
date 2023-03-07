package tests;


import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class ServiceTest {

    @Test
    public void testCase1(){
        Response response = get("https://reqres.in/api/user?page=2");

        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testCase2(){

        given().
                get("http://reqres.in/api/user?page=2").
                then().
                statusCode(200)
                .body("data.id[0]",equalTo(7));


    }

    @Test
    public void testCase3(){

        given().
                get("http://reqres.in/api/user?page=2").
                then().
                statusCode(200).
                log().all();

    }
}
