package com.testinium.step_definitions;


import com.github.javafaker.Bool;
import com.testinium.pages.PageLoad;
import com.testinium.utilities.BrowserUtils;
import com.testinium.utilities.ConfigurationReader;
import com.testinium.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.apache.poi.ss.formula.functions.BooleanFunction.OR;


public class Search {
PageLoad search = new PageLoad();
    String path = "Book1.xlsx";
    FileInputStream fileInputStream = new FileInputStream(path);
    XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
    XSSFSheet sheet = workbook.getSheet("Sheet1");
    String firstSearchWord = String.valueOf(sheet.getRow(0).getCell(0));
    String secondSearchWord = String.valueOf(sheet.getRow(0).getCell(1));

    String priceOfItem;

    FileWriter myFile = new FileWriter("productInfo.txt");

    public Search() throws IOException {
    }

    @Given("Verify page loads")
    public void verify_page_loads() {
        Driver.getDriver().get(ConfigurationReader.getProperty("web.url"));
        String currentURL = Driver.getDriver().getCurrentUrl();
        BrowserUtils.verifyElementDisplayed(By.className("o-modal__container"));
        Driver.getDriver().findElement(By.id("genderWomanButton")).click();

        BrowserUtils.verifyElementDisplayed(By.className("banner-actions-container"));
        Driver.getDriver().findElement(By.id("onetrust-accept-btn-handler")).click();

        Assert.assertEquals(ConfigurationReader.getProperty("web.url"), currentURL);

    }

    @Given("Navigate to search field")
    public void navigate_to_search_field() {

        BrowserUtils.waitFor(2);
        Driver.getDriver().findElement(By.xpath("//input[@placeholder]"));
    }

    @Given("Enter Şort to the field")
    public void enter_şort_to_the_field() throws IOException {

        BrowserUtils.waitFor(2);
        Driver.getDriver().findElement(By.xpath("//input[@placeholder]")).sendKeys(firstSearchWord);
        BrowserUtils.waitFor(2);

    }

    @Given("Delete the Şort word")
    public void delete_the_şort_word() {
        for (int i = 0; i < firstSearchWord.length(); i++) {
            Driver.getDriver().findElement(By.xpath("//input[@placeholder]")).sendKeys(Keys.BACK_SPACE);
        }
        //BrowserUtils.waitFor(5);

    }

    @Given("Enter Gömlek to the field")
    public void enter_gömlek_to_the_field() {
        Driver.getDriver().findElement(By.xpath("//input[@placeholder]")).sendKeys(secondSearchWord);

    }


    @Given("Click on Enter Key")
    public void click_on_enter_key() {
        Driver.getDriver().findElement(By.xpath("//input[@placeholder]")).sendKeys(Keys.ENTER);
        BrowserUtils.waitFor(2);
    }

    @Given("Choose randomly one product")
    public void choose_randomly_one_product() {

        List<WebElement> productList = Driver.getDriver().findElements(By.className("o-productList__item"));
        int maxProducts = productList.size();
        Random random = new Random();
        int randomProduct = random.nextInt(maxProducts);
        productList.get(randomProduct).click();
        BrowserUtils.waitFor(2);
    }


    @Given("Write on txt page some information about product")
    public void write_on_txt_page_some_information_about_product() throws IOException {

        String nameOfItem = Driver.getDriver().findElement(By.className("o-productDetail__description")).getText();
        priceOfItem = Driver.getDriver().findElement(By.id("priceNew")).getText();
        System.out.println(nameOfItem);
        System.out.println(priceOfItem);
        myFile.write(nameOfItem + "\n");
        myFile.write(priceOfItem);
        myFile.close();
        BrowserUtils.waitFor(2);
    }

    @Given("Send to cart this product")
    public void send_to_cart_this_product() {
        BrowserUtils.waitFor(2);

        Driver.getDriver().findElement(By.xpath("//div[@class='m-variation']//span[not(contains(@class,'disabled'))][1]")).click();
        Driver.getDriver().findElement(By.xpath("//button[@id='addBasket']")).click();
        BrowserUtils.waitFor(2);
    }


    @Given("Verify the price on cart and on page")
    public void verify_the_price_on_cart_and_on_page() {
        //priceOfItem = Driver.getDriver().findElement(By.className("m-price__list")).getText();
        Driver.getDriver().navigate().to("https://www.beymen.com/cart");

        String price = Driver.getDriver().findElement(By.xpath("//li[contains(@class, '-grandTotal')]//span[@class='m-orderSummary__value']")).getText();
        BrowserUtils.waitFor(2);
        System.out.println(price);
        System.out.println(priceOfItem);
        Assert.assertEquals(price, priceOfItem);
        BrowserUtils.waitFor(2);

    }


    @Given("Increase the piece of the product")
    public void increase_the_piece_of_the_product() {
        Select pieces = new Select(Driver.getDriver().findElement(By.xpath("//select[@id='quantitySelect0-key-0']")));
        pieces.selectByValue("2");
        BrowserUtils.waitFor(2);
    }


    @Given("Verify the pieces as {int}")
    public void verify_the_pieces_as(Integer int1) {
        BrowserUtils.waitFor(2);
        Select selected = new Select(Driver.getDriver().findElement(By.xpath("//select[@id='quantitySelect0-key-0']")));
        String actualSelected = selected.getFirstSelectedOption().getText();
        Assert.assertEquals(actualSelected,"2 adet");
    }


    @Given("Delete products on cart")
    public void delete_products_on_cart() {

        Driver.getDriver().findElement(By.id("removeCartItemBtn0-key-0")).click();
        BrowserUtils.waitFor(2);
    }
    @Then("Verify that the cart is empty")
    public void verify_that_the_cart_is_empty() {
        Boolean emptyCart = Driver.getDriver().findElement(By.id("emtyCart")).isEnabled();
        Boolean actualCart = true;
        Assert.assertEquals(emptyCart,actualCart);
    }

}
