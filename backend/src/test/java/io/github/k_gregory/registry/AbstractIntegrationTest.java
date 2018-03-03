package io.github.k_gregory.registry;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Category(IntegrationCategory.class)
@SpringBootTest
@ActiveProfiles("integration-test")
public abstract class AbstractIntegrationTest {
}
