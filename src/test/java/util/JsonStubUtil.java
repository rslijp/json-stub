package util;

import com.github.tomakehurst.wiremock.WireMockServer;
import nl.rabobank.powerofattorney.stub.JsonStub;

public abstract class JsonStubUtil {


    private static WireMockServer wireMockServer;

    public static void start() {
        if (wireMockServer != null) {
            return;
        }
        wireMockServer = JsonStub.start();
    }

    public static void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
