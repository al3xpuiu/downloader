package com.nextlevel.portal.nexusreleasedownloader.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModuleFactoryTest {

    private Factory factory;

    private static final String EXPECTED = "http://nexus.next-level-apps.com:8081/nexus/service/local/repositories/releases/content/com/nextlevel/portal/acquisition/3.2.17.1/acquisition-3.2.17.1.zip";
    private static final String BASE_URL = "http://nexus.next-level-apps.com:8081/nexus/service/local/repositories/releases/content/com/nextlevel/portal";
    private static final String VERSION = "3.2.17.1";
    private static final String MODULE_NAME = "acquisition";
    private static final String FILE_EXTENSION = ".zip";

    @Before
    public void setUp() throws Exception {
        factory = new ModuleFactory(BASE_URL, VERSION);
    }

    @Test
    public void getModuleTest() {
        //When
        Module module = factory.getModule(MODULE_NAME, ModuleFactory.Scope.RELEASE, FILE_EXTENSION);

        //Then
        Assert.assertEquals(EXPECTED, module.getUrl());
    }
}
