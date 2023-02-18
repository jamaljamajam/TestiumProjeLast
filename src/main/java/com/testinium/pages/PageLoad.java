package com.testinium.pages;

import com.testinium.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLoad {
    public PageLoad(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

  @FindBy(xpath = "//input[@placeholder]")
      public WebElement searchInputField;

   // @FindBy(className = "o-productList__item")
    //public WebElement productList;

    @FindBy(className = "o-productDetail__description")
    public WebElement productDetails;

    @FindBy(id = "priceNew")
    public WebElement productPrice;

    @FindBy(xpath = "//div[@class='m-variation']//span[not(contains(@class,'disabled'))][1]")
    public WebElement productSize;

    @FindBy(xpath ="//button[@id='addBasket']")
    public WebElement addBasketButton;

    @FindBy(xpath ="//li[contains(@class, '-grandTotal')]//span[@class='m-orderSummary__value']")
    public WebElement productPriceOnCart;

    @FindBy(xpath ="//select[@id='quantitySelect0-key-0']")
    public WebElement updatePiece;

    @FindBy(id = "emtyCart")
    public WebElement emptyCart;

  @FindBy(id = "removeCartItemBtn0-key-0")
  public WebElement deleteProduct;
}
