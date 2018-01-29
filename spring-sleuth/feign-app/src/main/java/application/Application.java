package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@RestController
public class Application {

    @Autowired
    private DelayClient delayClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @NewSpan
    @RequestMapping("/feign/{time}")
    public ResponseEntity feign(@PathVariable int time) {
        return delayClient.sleep(time);
    }
}
