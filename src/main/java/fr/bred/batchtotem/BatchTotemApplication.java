package fr.bred.batchtotem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchTotemApplication {

    /**
     * The main method.
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(BatchTotemApplication.class, args)));
    }

}
