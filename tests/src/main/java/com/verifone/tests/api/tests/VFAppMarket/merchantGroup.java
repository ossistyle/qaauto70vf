package com.verifone.tests.api.tests.VFAppMarket;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import com.verifone.utils.apiClient.DataDrivenApi;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.utils.apiClient.DataDrivenApi.setFilePath;

public class merchantGroup extends BaseTest {


    private String file;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("merchant-groupVFMPQA.xls", "merchant-groupVFMPDEV.xls");
    }

    @DataProvider(name = "return_Group")
    public Object[][] returnGroup() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "returnGroup-gvcca2307");
        return arrayObject;
    }

    @DataProvider(name = "create_and_edit_group")
    public Object[][] createEditGroup() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "createEditGroup-gvcca2305");
        return arrayObject;
    }

    @DataProvider(name = "get Group Details")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "getDeviceGroupDetails-gvcca2308");
        return arrayObject;
    }

    @Test(enabled = true, dataProvider = "return_Group", groups = "cloudApi1")

    public void getGroupDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(enabled = true, dataProvider = "create_and_edit_group", groups = "cloudApi1")

    public void postGetGroupDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList);
    }

    @Test(enabled = true, dataProvider = "get Group Details", groups = "VFMPapi")

    public void cloudApiLocationDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(), false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess_ValidateExcludeData(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList);
    }

}
