/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spring.boot.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Gary Russell
 * @since 2.3
 */
class EmbeddedKafkaBrokerTests {

    @Test
    void testUpDown() {
        EmbeddedKafkaBroker kafka = new EmbeddedKafkaBroker(1);
        kafka.afterPropertiesSet();
        System.out.println("1 ZookeeperConnectionString: " + kafka.getZookeeperConnectionString());
        assertThat(kafka.getZookeeperConnectionString()).startsWith("127");

        kafka.destroy();
        assertThat(kafka.getZookeeperConnectionString()).isNull();
        System.out.println("2 ZookeeperConnectionString: " + kafka.getZookeeperConnectionString());
    }

}
