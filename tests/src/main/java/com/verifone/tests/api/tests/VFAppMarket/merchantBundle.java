package com.verifone.tests.api.tests.VFAppMarket;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class merchantBundle extends BaseTest {


    private String file;
    protected String offerId;


    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("noFilehere.xls", "merchant-bundleVFMP.xls");
    }

/*  @DataProvider(name = "bundles list")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "returnBundles-gvcca2319");
        return arrayObject;
    }

    @DataProvider(name = "negative_test")
    public Object[][] negative_test() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "NegativeTest");
        return arrayObject;
    }

    @DataProvider(name = "freeAppsBundle")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "freeapps");
        return arrayObject;
    }   */

    @DataProvider(name = "existing_assignment")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "existing_assignment");
        return arrayObject;
    }

/*
    @Test(dataProvider = "bundles list", groups = "cloudApi1")

    public void cloudApiLocationDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }


    @Test(dataProvider = "negative_test", groups = "cloudApi1")

    public void negativeTestDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }

    @Test(dataProvider = "freeAppsBundle", groups = "cloudApi1")

    public void freeBundleDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                String headers, String headersForGetToken, String body, String expectedStatusCode,
                                String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }   */
@Test(dataProvider = "existing_assignment", groups = "cloudApi1")

public void existingAssignmentDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                          String headers, String headersForGetToken, String body, String expectedStatusCode,
                          String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
    starTestLog(rowNum + ". " + comments, comments);

    DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
    offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
            expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
}


}
