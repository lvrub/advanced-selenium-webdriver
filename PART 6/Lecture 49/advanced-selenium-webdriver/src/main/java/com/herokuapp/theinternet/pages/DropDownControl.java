package com.herokuapp.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownControl extends BaseControl {

    BasePageObject pageObject;

    public DropDownControl(BasePageObject pageObject) {
        super(pageObject);
        this.pageObject =pageObject;
    }

    public void selectOptionByValue(int i, By dropdown) {
        WebElement dropdownElement = this.pageObject.find(dropdown);
        Select select = new Select(dropdownElement);
        select.selectByValue("" + i);
    }

    public void selectByVisibleText(int i, By dropdown) {
        WebElement dropdownElement = this.pageObject.find(dropdown);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText("Option " + i);
    }
}
