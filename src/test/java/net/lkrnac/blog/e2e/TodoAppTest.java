package net.lkrnac.blog.e2e;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import net.lkrnac.blog.TodoApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by: Lubos Krnac
 * Date: 2016-07-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebIntegrationTest
public class TodoAppTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testCreateToDos() {
        // GIVEN
        new TodoPageObject(driver)
            .get()

            // WHEN
            .addTodo("testTodo1")
            .addTodo("testTodo2")

            // THEN
            .verifyTodoShown("testTodo1")
            .verifyTodoShown("testTodo2");
    }
}
