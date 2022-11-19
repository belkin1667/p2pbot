package com.l2lhackathon.peers;

import java.time.Clock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.l2lhackathon.peers.configuration.CleanupDataBaseAfterEachTestExtension;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.support.TransactionTemplate;


@AutoConfigureMockMvc
@ActiveProfiles("integration-tests")
@PropertySource("classpath:application.properties")
@ExtendWith(CleanupDataBaseAfterEachTestExtension.class)
@SpringBootTest(classes = PeersApplication.class)
public abstract class BasePeersIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected TransactionTemplate transactionTemplate;

    protected ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @SneakyThrows
    protected String toJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    protected <T> T toObject(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }

}
