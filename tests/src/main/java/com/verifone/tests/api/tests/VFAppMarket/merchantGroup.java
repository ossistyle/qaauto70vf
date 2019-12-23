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
    private static String id;
    private ExtentTest testLog;

    @BeforeSuite
    private void getFile()
    {
        file = setFilePath("merchant-groupVFMPQA.xls", "noFileHere.xls");
    }

  /*  @DataProvider(name = "list_of_groups")
    public Object[][] returnGroup() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "returnGroup-gvcca2307");
        return arrayObject;
    }*/

    @DataProvider(name = "create_and_edit_group")
    public Object[][] createEditGroup() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "createEditGroup-gvcca2305");
        return arrayObject;
    }

    @DataProvider(name = "add_get_remove")
    public Object[][] location() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(file, "DeviceToGroupGVCCA2308");
        return arrayObject;
    }

    @Test(enabled = true, dataProvider = "add_get_remove", groups = "VFMPapi",description = "add get remove group")

    public void add_get_removeDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String comments, String rowNum, String param) throws Exception {

        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        String responseParam = api.startProsessGetId(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, param);
        if(responseParam !=null)setId(responseParam);
    }

    @Test(enabled = true, dataProvider = "create_and_edit_group", groups = "VFMPapi")

    public void postGroupDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String comments, String rowNum, String param) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);

        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(),false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        String responseParam = api.startProsessGetId(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, param);
        if(responseParam !=null)setId(responseParam);
    }



/*    @Test(enabled = true, dataProvider = "list_of_groups", groups = "VFMPapi")

    public void list_of_groupsDDT(String accessToken, String accGrantType, String accSSOURL, String uri, String requestMethod,
                                    String headers, String headersForGetToken, String body, String expectedStatusCode,
                                    String expectedResult, String verifyList, String verifyExcludeList, String comments, String rowNum) throws Exception {
        starTestLog(rowNum + ". " + comments, comments);


        DataDrivenApi api = new DataDrivenApi((ExtentTest) test.get(), false); // 'isBearer' is a flag to define a getToken type(with 'Bearer' or not)
        api.startProsess_ValidateExcludeData(accessToken, accGrantType, accSSOURL, uri, requestMethod, headers, headersForGetToken, body,
                expectedStatusCode, expectedResult, verifyList, verifyExcludeList);
    }*/


    private void setId(String responseParam){
        this.id=responseParam;
    }

    public static String getId(){
        return id;
    }

}
