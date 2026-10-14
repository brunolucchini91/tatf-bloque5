package com.tatf.adminces.modules.access.task;

import com.tatf.adminces.modules.access.data.AccessData;
import com.tatf.adminces.modules.access.pom.AccessPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class AccessTask {

    private final IBrowser browser;
    private final AccessPO access;

    public AccessTask(IBrowser browser) {
        this.browser = browser;
        this.access = new AccessPO(browser);
    }

    public void enterToSystem() {
        browser.interaction().navigateTo(AccessData.URL);

        access.waitForPasswordInput();
        access.enterPassword(AccessData.ACCESS_HASH);
        access.clickEnter();
        access.waitForTitle();
    }

    public void verifyAccessPage() {
        IVerify.create().verify(
                AccessData.TITLE,
                access.getTitle(),
                "No se accedió correctamente a AdminCES."
        );
    }
}