package com.tatf.adminces.modules.users.pom;

import com.tatf.core.browser.IBrowser;
import org.openqa.selenium.Keys;

public class UsersPO {

    private final IBrowser browser;

    private final String title =
            "//*[contains(@class,'title')]";

    private final String tableBody = "bodyTable";

    public UsersPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitForPage() {
        browser.wait(tableBody).id();
    }

    public String getTitle() {
        return browser.find().xpath(title).getText();
    }

    public String getTableText() {
        return browser.find().id(tableBody).getText();
    }

    public void scrollToUser(String email) {
        browser.interaction().javaScriptExecutor(
                "document.getElementById('" + email + "')" +
                        ".scrollIntoView({block: 'center'});"
        );
    }

    public void clickDeleteUser(String email) {
        browser.wait(email).id();
        browser.find().id(email).write(Keys.ENTER.toString());
    }
}