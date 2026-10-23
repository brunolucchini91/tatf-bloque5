package com.tatf.adminces.suites;

import com.tatf.adminces.modules.createuser.test.CrearTesterTest;
import com.tatf.adminces.modules.users.test.EliminarTesterTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite por clase: Gestión de Testers (alta y baja)")
@SelectClasses({CrearTesterTest.class, EliminarTesterTest.class})
public class SuiteTesters {
}
