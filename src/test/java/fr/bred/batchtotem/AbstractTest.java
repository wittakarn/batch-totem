package fr.bred.batchtotem;

import org.junit.runner.RunWith;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class AbstractTest.
 */
@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public abstract class AbstractTest {

}
