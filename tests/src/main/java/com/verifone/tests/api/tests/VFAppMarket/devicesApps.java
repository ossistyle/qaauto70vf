package com.verifone.tests.api.tests.VFAppMarket;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class devicesApps extends BaseTest {


    private String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("noFilehere.xls", "device-appsVFMP.xls");
    }

    @DataProvider(name = "return Devices with Apps assigned")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "getAppsMerchant-gvcca-2312");
        return arrayObject;
    }

    @Test(enabled = true, dataProvider = "return Devices with Apps assigned", groups = "VFMPapi")

    public void return_Devices_with_AppsDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, "Return devices with apps assigned");


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess_ValidateExcludeData(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList);
    }

}
