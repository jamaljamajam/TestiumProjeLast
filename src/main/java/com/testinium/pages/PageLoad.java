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

 //


}
