package stub;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import nl.rabobank.powerofattorney.stub.JsonStub;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class RestAssuredTestBase {

    private static WireMockServer wireMockServer;

    private static boolean INITIALIZED = false;

    public static void init() {
        if(INITIALIZED) return;
        INITIALIZED=true;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = 8080;
        RestAssured.requestSpecification=new RequestSpecBuilder().setPort(RestAssured.port).addHeader("Content-Type", "application/json").build();
    }

    @BeforeClass
    public static void setUp()
    {
        init();
//        wireMockServer = JsonStub.start();
    }

    @AfterClass
    public static void tearDown()
    {
        if(wireMockServer!=null){
            wireMockServer.stop();
        }
    }
}
