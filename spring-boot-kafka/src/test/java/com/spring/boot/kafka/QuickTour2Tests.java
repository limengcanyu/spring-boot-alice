package com.spring.boot.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * <p>Description: With Java Configuration</p>
 *
 * @author rock.jxf
 * @date 2020/12/9 21:37
 */
public class QuickTour2Tests {

    @Autowired
    private Listener listener;

    @Autowired
    private KafkaTemplate<Integer, String> template;

    @Test
    public void testSimple() throws Exception {
        template.send("annotated1", 0, "foo");
        template.flush();
        assertTrue(this.listener.latch1.await(10, TimeUnit.SECONDS));
    }
}
