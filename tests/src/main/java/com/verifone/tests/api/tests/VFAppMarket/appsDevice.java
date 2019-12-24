package com.verifone.tests.api.tests.VFAppMarket;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class appsDevice extends BaseTest {


    private String file;
    protected String offerId;


    //appsVFMP in use also with appsMerchant class
    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("noFilehere.xls", "appsVFMP.xls");
    }

    @DataProvider(name = "return Apps per Device")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "getAppsDevice-gvcca-2311");
        return arrayObject;
    }

    @Test(enabled = true, dataProvider = "return Apps per Device", groups = "VFMPapi")

    public void cloudApiLocationDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, "Return apps per device");


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess_ValidateExcludeData(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList);
    }

    @DataProvider(name = "add Apps to Device")
    public Object[][] location1() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "postAppsDevice-gvcca-2310");
        return arrayObject;
    }

    @Test(enabled = true, dataProvider = "add Apps to Device", groups = "VFMPapi")

    public void cloudApiLocation1DDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, "Add apps to device");


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        offerId = api.startProsess_ValidateExcludeDataEvaluaet(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList, offerId, rowNum);
    }

}
