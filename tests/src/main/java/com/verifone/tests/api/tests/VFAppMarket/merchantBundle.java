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
        file = setFilePath("merchant-bundleVFMPQA.xls", "merchant-bundleVFMP.xls");
    }


    @DataProvider(name = "bundles list")
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
    public Object[][] freeAppsBundle() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "freeapps");
        return arrayObject;
    }


    @DataProvider(name = "existing_assignment")
    public Object[][] existing_assignment() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "existing_assignment");
        return arrayObject;
    }


    @DataProvider(name = "one_time_payment")
    public Object[][] one_time_payment() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "one_time_payment");
        return arrayObject;
    }


    @DataProvider(name = "1time_existing")
    public Object[][] OneTime_existing() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "1time_existing");
        return arrayObject;
    }


    @DataProvider(name = "recurring")
    public Object[][] recurring() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "recurring");
        return arrayObject;
    }


    @DataProvider(name = "recurring_existing")
    public Object[][] recurring_existing() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "recurring_existing");
        return arrayObject;
    }


    @DataProvider(name = "mixed")
    public Object[][] mixed() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "mixed");
        return arrayObject;
    }


    @DataProvider(name = "mixed_existing")
    public Object[][] mixed_existing() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "mixed_existing");
        return arrayObject;
    }


    @Test(dataProvider = "bundles list", groups = "VFMPapi")

    public void bundlesListDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                               String headers, String headersForGetToken, String body, String expectedStatusCode,
                               String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }



    @Test(dataProvider = "negative_test", groups = "VFMPapi")

    public void negativeTestDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                   String headers, String headersForGetToken, String body, String expectedStatusCode,
                                   String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }

    @Test(dataProvider = "freeAppsBundle", groups = "VFMPapi")

    public void freeBundleDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                String headers, String headersForGetToken, String body, String expectedStatusCode,
                                String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }


    @Test(dataProvider = "existing_assignment", groups = "VFMPapi")

    public void existingAssignmentDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                          String headers, String headersForGetToken, String body, String expectedStatusCode,
                          String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
    starTestLog(rowNum + ". " + comments, comments);

    DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
    offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
            expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }


    @Test(dataProvider = "one_time_payment", groups = "VFMPapi")

    public void one_time_paymentDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                      String headers, String headersForGetToken, String body, String expectedStatusCode,
                                      String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }


    @Test(dataProvider = "1time_existing", groups = "VFMPapi")

    public void one_time_existingDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }


    @Test(dataProvider = "recurring", groups = "VFMPapi")

    public void recurringDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                     String headers, String headersForGetToken, String body, String expectedStatusCode,
                                     String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }

    @Test(dataProvider = "recurring_existing", groups = "VFMPapi")

    public void recurring_existingDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                             String headers, String headersForGetToken, String body, String expectedStatusCode,
                             String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }



    @Test(dataProvider = "mixed", groups = "VFMPapi")

    public void mixedDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                      String headers, String headersForGetToken, String body, String expectedStatusCode,
                                      String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }


    @Test(dataProvider = "mixed_existing", groups = "VFMPapi")

    public void mixed_existingDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                         String headers, String headersForGetToken, String body, String expectedStatusCode,
                         String expectedResult, String verifyList,String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false);
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList,verifyExcludeList, offerId, rowNum );
    }


}
