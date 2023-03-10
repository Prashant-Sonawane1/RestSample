package tests;


import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
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
                .body("data.id[0]",equalTo(7))
                .body("data.name",hasItems("turquoise", "honeysuckle"));

    }

    @Test
    public void testCase3(){

        given().
                get("http://reqres.in/api/user?page=2").
                then().
                statusCode(200).
                log().all();

    }

    @Test
    public void test_POST_1(){

        JSONObject request = new JSONObject();


        request.put("email","michael.lawson@reqres.in");
        request.put("password","TestPass12");

        System.out.println(request.toString());

        given().
                body(request.toString()).
                contentType(ContentType.JSON).
        when().
                post("https://reqres.in/api/register").
        then().
                statusCode(200).log().all();

    }

    @Test
    public void test_AUTH_1(){

        given().
                header("X-RapidAPI-Key","b6031ba2bcmsh6a4d56369a56bb5p17cb66jsnca577790d09b").
                header("X-RapidAPI-Host","wft-geo-db.p.rapidapi.com").
                get("https://wft-geo-db.p.rapidapi.com/v1/geo/cities").
                then().
                statusCode(200).
                log().all();
    }


}
