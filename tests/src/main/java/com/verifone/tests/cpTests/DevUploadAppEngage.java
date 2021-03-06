package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.verifone.tests.steps.Steps.createAndroidApp;
import static com.verifone.tests.steps.Steps.createEngageApp;
import static com.verifone.tests.steps.Steps.devLogin;


public class DevUploadAppEngage extends BaseTest {


    @Test(testName = "Dev admin upload an Engage app", description = "Engage app for CP")
    public void devUploadEngageAppUI() throws IOException, InterruptedException, AWTException {
//        Company dev = new Company();
//        dev.setUserName("doba@cmail.club");
//        dev.setPassword("Welcome3#");
        User dev = EntitiesFactory.getEntity("DevAdmin");
        devLogin(dev);
        createEngageApp();
    }

//    @Test(testName = "Dev support admin reject app", description = "After Dev admin upload an app",
//            dependsOnMethods = {"devUploadEngageAppUI"})
//    public void devSupportRejectAppUI() throws Exception {
//        devSupportAdminLogin();
//        System.out.println("sdfsdf");
//    }
}
