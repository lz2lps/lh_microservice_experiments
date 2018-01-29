package application;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "delay", url = "http://delay-service:8080", fallback = DelayClient.DelayClientFallback.class)
public interface DelayClient {

    @RequestMapping(value = "/sleep/{time}", method = RequestMethod.GET)
    ResponseEntity sleep(@PathVariable("time") int time);

    @Component
    class DelayClientFallback implements DelayClient {

        @NewSpan
        @Override
        public ResponseEntity sleep(int time) {
            return new ResponseEntity("Fallback\n", HttpStatus.OK);
        }
    }
}
