package com.verifone.tests.api.tests.VFAppMarket;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class bundleCRUD extends BaseTest {

    private String file;
    private String bundleId = "";

    @BeforeSuite
    private void getFile() {
        file = setFilePath("noFilehere.xls", "bundlesVFMP.xls");
    }


    @DataProvider(name = "return bundle CRUD APIs")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "bundle-CRUD-GVCCA-2545");
        return arrayObject;
    }


    @Test(enabled = true, dataProvider = "return bundle CRUD APIs", groups = "VFMPapi")
    public void verifyBundleCRUDApisDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                        String headers, String headersForGetToken, String body, String expectedStatusCode,
                                        String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {

        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(), false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)

        bundleId = api.bundlesCrudVerification(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList, rowNum, comments, bundleId);

    }

}
