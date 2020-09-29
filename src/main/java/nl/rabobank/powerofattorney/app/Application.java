package nl.rabobank.powerofattorney.app;

import nl.rabobank.powerofattorney.stub.JsonStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Booting application on 9080");
        SpringApplication.run(Application.class, args);
        logger.info("Booting json stub on 8080");
        JsonStub.start();
    }

}
