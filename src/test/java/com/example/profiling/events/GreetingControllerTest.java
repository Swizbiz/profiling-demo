package com.example.profiling.events;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingStream;
import org.awaitility.Awaitility;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateEventWhenGetRequest() throws Exception {
        final String name = "Alice";
        try (RecordingStream rs = new RecordingStream()) {
            final AtomicReference<RecordedEvent> ref = new AtomicReference<>();
            rs.onEvent(GreetingEvent.NAME, ref::set);
            rs.startAsync();

            mockMvc.perform(get("/greetings/{name}", name))
                .andExpect(status().isOk());

            Awaitility.waitAtMost(3, TimeUnit.SECONDS).until(() -> ref.get() != null);
            final RecordedEvent event = ref.get();
            assertTrue(event.getDuration().toNanos() > 0);
            assertEquals(
                event.getString("name"),
                name
            );
            assertEquals(
                event.getLong("size"),
                name.length()
            );
        }
    }

}