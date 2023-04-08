package noelspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class HelloRepositoryTest {

    @Autowired HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("n")).isNull();
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
