package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class ApiSteps {
    private Response response;

    @Given("the user sends a GET request to the joke api")
    public void sendGetRequest() {
        // RestAssured uses a clean given/when/then syntax for execution
        response = RestAssured
                .given()
                .baseUri("https://v2.jokeapi.dev")
                .when()
                .get("/joke/Any")
                .then()
                .extract().response();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals("Status code mismatch!", expectedStatusCode, actualStatusCode);
    }

    @Then("the joke response should contain a valid setup and delivery")
    public void verifyJokePayload() {
        // 1. Verify the 'error' flag is explicitly false
        boolean hasError = response.jsonPath().getBoolean("error");
        Assert.assertFalse("The API returned an error flag!", hasError);

        // 2. Check what TYPE of joke was returned dynamically
        String jokeType = response.jsonPath().getString("type");

        if ("twopart".equals(jokeType)) {
            // Handle a two-part joke
            String setup = response.jsonPath().getString("setup");
            String delivery = response.jsonPath().getString("delivery");

            Assert.assertNotNull("Setup is missing from payload!", setup);
            Assert.assertNotNull("Delivery is missing from payload!", delivery);

            System.out.println("--- TWOPART JOKE ---");
            System.out.println("Setup: " + setup);
            System.out.println("Delivery: " + delivery);
        } else {
            // Handle a single-line joke safely so the test doesn't crash
            String singleJoke = response.jsonPath().getString("joke");

            Assert.assertNotNull("Joke text is missing from single payload!", singleJoke);

            System.out.println("--- SINGLE JOKE ---");
            System.out.println("Joke: " + singleJoke);
        }
        System.out.println("----------------------");
    }}