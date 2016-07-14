package net.lkrnac.blog.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by: Lubos Krnac
 * Date: 2016-07-14.
 */
public class TodoPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    public TodoPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public TodoPageObject get() {
        driver.get("localhost:8080");
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));
        return this;
    }

    public TodoPageObject addTodo(String todo) {
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys(todo);
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();
        return this;
    }

    public TodoPageObject clickOnTodoItem(String todoItem) {
        findTodo(todoItem).click();
        return this;
    }

    public TodoPageObject verifyTodoShown(String todoItem, boolean expectedStrikethrough) {
        WebElement todoElement = findTodo(todoItem);
        assertNotNull(todoElement);
        boolean actualStrikethrough = todoElement.getAttribute("style").contains("text-decoration: line-through;");
        assertEquals(expectedStrikethrough, actualStrikethrough);
        return this;
    }

    public TodoPageObject verifyTodoNotShown(String todoItem) {
        assertNull(findTodo(todoItem));
        return this;
    }

    private WebElement findTodo(String todoItem) {
        return driver.findElement(By.xpath(format("//*[text()='%s']", todoItem)));
    }
}
