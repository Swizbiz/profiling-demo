package com.example.profiling.events;

import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("greetings/{name}")
    public String greetings(@PathVariable("name") String name) throws InterruptedException {
        final GreetingEvent event = new GreetingEvent();
        if (event.isEnabled()) {
            event.name = name;
            event.invokeDate = System.currentTimeMillis();
            event.size = name.getBytes(StandardCharsets.UTF_8).length;
            event.classValue = this.getClass();
        }
        try {
            event.begin();
            //2 methods with different exec time
            if (name.startsWith("A")) {
                GreetingController.doWork();
            } else {
                GreetingController.doMoreWork();
            }
            return String.format("Hello, dear %s!", name);
        } finally {
            event.end();
            event.commit();
        }
    }

    private static void doWork() throws InterruptedException {
        Thread.sleep(300L);
    }

    private static void doMoreWork() throws InterruptedException {
        Thread.sleep(3_000L);
    }

}
