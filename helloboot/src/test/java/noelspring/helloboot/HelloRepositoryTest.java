package noelspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
class HelloRepositoryTest {

    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @BeforeEach
    void init() {
        jdbcTemplate.execute(
                "create table if not exists hello" +
                        "(name varchar (50) primary key, " +
                        "count int)");
    }

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("noel")).isNull();
    }

    @Test
    void increaseCount() {
        Assertions.assertThat(helloRepository.countOf("noel")).isEqualTo(0);

        helloRepository.increaseCount("noel");
        Assertions.assertThat(helloRepository.countOf("noel")).isEqualTo(1);

        helloRepository.increaseCount("noel");
        Assertions.assertThat(helloRepository.countOf("noel")).isEqualTo(2);
    }
}