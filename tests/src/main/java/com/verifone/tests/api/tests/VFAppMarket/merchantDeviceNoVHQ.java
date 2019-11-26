package com.verifone.tests.api.tests.VFAppMarket;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class merchantDeviceNoVHQ extends BaseTest {


    private String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("merchant-deviceVFMP.xls", "noFileHere.xls");
    }

    @DataProvider(name = "return Devices per Merchant")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "getDevicesMerchant-gvcca-2309");
        return arrayObject;
    }

    @Test(dataProvider = "return Devices per Merchant", groups = "VFMPapi")

    public void cloudApiLocationDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess_ValidateExcludeData(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList);
    }

}
