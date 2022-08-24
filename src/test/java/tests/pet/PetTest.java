package tests.pet;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@Epic("Swagger.io test")
@Feature("Pet test")
public class PetTest extends tests.pet.BaseTest {

    public String getPath(String path) throws IOException {
        return new String(Files.readAllBytes(Path.of(path)));
    }

    @Test(priority = 0)
    @Description("POST pet testing")
    public void postPet() throws IOException {
        String jsonBodyPost = getPath("src/test/java/data/pet/petPost.json");

        given()
                .spec(requestSpecification)
                .body(jsonBodyPost)
                .when()
                .post("")
                .then()
                .statusCode(200)
                .body("id", is(123))
                .body("name", is("Lord Voldemort"))
                .body("category.name", is("dog"))
                .body("status", is("available"));

    }

    @Test(priority = 1)
    @Description("GET pet testing")
    public void getPet() {
        int petId = 123;
        given()
                .spec(requestSpecification)
                .when()
                .get("/" + petId)
                .then()
                .statusCode(200)
                .body("name", is("Lord Voldemort"));
    }

    @Test(priority = 2)
    @Description("PUT pet testing")
    public void putPet() throws IOException {
        String jsonBodyPut = getPath("src/test/java/data/pet/petPut.json");

        given()
                .spec(requestSpecification)
                .body(jsonBodyPut)
                .when()
                .put("")
                .then()
                .statusCode(200)
                .body("status", is("sold"));
    }

    @Test(priority = 3)
    @Description("DELETE pet testing")
    public void deletePet() {
        int petId = 123;
        given()
                .spec(requestSpecification)
                .when()
                .delete("/" + petId)
                .then()
                .statusCode(200);
    }
}