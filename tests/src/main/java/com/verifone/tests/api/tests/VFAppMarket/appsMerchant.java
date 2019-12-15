package com.verifone.tests.api.tests.VFAppMarket;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class appsMerchant extends BaseTest {


    private String file;
    protected String offerId;


    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("appsVFMP_QA.xls", "appsVFMP.xls");
    }

   @DataProvider(name = "return_Apps_per_Merchant")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "getAppsMerchant-gvcca2304");
        return arrayObject;
    }

    @DataProvider(name = "return_owned_Merchant_Apps")
    public Object[][] ownedApps() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "getAppsMerchantOwned-gvcca-2313");
        return arrayObject;
    }

    @Test(dataProvider = "return_Apps_per_Merchant", groups = "VFMPapi")
    public void cloudApiLocationDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess_ValidateExcludeData(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList);
    }

    @Test(dataProvider = "return_owned_Merchant_Apps", groups = "VFMPapi")
    public void returnOwnedAppsMerchantDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList,offerId,rowNum);
    }

}
