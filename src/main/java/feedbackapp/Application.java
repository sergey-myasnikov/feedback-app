package feedbackapp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Spring supports embedding the Tomcat servlet 
 * container as the HTTP runtime, instead of 
 * deploying to an external instance.
 * @author Sergey Myasnikov
 *
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
