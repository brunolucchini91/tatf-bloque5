package com.tatf.adminces.suites;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite por paquete: Administradores (registro y reinicio de contraseña)")
@SelectPackages({
        "com.tatf.adminces.modules.register",
        "com.tatf.adminces.modules.resetpassword"
})
public class SuiteAdministradores {
}
