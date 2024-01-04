package com.herokuapp.theinternet.pages;

import org.openqa.selenium.By;

public class ButtonControl extends BaseControl {

    BasePageObject pageObject;

    public ButtonControl(BasePageObject pageObject) {
        super(pageObject);
    }

    protected void click(By locator) {
        pageObject.waitForVisibilityOf(locator, 5);
        pageObject.find(locator).click();
    }
}
