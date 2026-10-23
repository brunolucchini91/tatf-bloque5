package com.tatf.adminces.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite por tag: Humo")
@SelectPackages("com.tatf.adminces.modules")
@IncludeTags("humo")
public class SuiteHumo {
}
