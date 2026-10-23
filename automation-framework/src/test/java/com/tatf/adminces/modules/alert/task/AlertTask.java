package com.tatf.adminces.modules.alert.task;

import com.tatf.adminces.modules.alert.pom.AlertPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class AlertTask {

    private final AlertPO alert;

    public AlertTask(IBrowser browser) {
        this.alert = new AlertPO(browser);
    }

    public void verify(String expectedTitle, String expectedMessage) {
        alert.waitForAlert();

        IVerify.create().verify(
                expectedTitle,
                alert.getTitle(),
                "El título de la alerta no es el esperado."
        );

        IVerify.create().verify(
                expectedMessage,
                alert.getMessage(),
                "El mensaje de la alerta no es el esperado."
        );
    }

    public void confirm() {
        alert.clickConfirm();
        alert.waitForClose();
    }

    public void confirmQuestion() {
        alert.clickConfirm();
    }
}