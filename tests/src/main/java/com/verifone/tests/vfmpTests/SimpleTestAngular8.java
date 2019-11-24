package com.verifone.tests.vfmpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.vfMpPages.SimpleTestAngular8Page;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

public class SimpleTestAngular8 extends BaseTest {

    @Test(priority = 0,testName = "selection option", description = "POC for testing new Verifone MP")
    public void merchantPortal(){

        User dev = EntitiesFactory.getEntity("VFMP");
        SimpleTestAngular8Page verifoneMp = (SimpleTestAngular8Page) PageFactory.getPage("SimpleTestAngular8Page");

        verifoneMp.openChrome();
        verifoneMp.testAngular();


    }
}


