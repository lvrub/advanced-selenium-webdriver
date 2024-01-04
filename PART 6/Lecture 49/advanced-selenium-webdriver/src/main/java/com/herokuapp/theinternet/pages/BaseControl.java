package com.herokuapp.theinternet.pages;

import org.openqa.selenium.WebElement;

public class BaseControl extends Controls {

    BasePageObject basePageObject;

    public BaseControl(BasePageObject pageObject) {
        this.basePageObject = pageObject;
    }

    WebElement focusOnControl() {
        return basePageObject.driver.switchTo().activeElement();
    }
}
