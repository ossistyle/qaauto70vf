package com.verifone.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

//import com.verifone.entities.*;
public class EnvConfig {

    private String env;
    private String portal;
    private ApiUrls apiUrls;
    private String webUrl;
    private Credentials credentials;
    private Properties prop = new Properties();

    private String appName;
    private String oneTimeAppName;
    private String freeEditionApp;
    private String monthlyRecurringApp;
    private String yearlyRecurringApp;
    private String oneTimePayFreeTrialApp;
    private String monthlyRecurringFreeTrialApp;
    private String yearlyRecurringFreeTrialApp;
    private String cmFiveDeviceSerialNo01;
    private String deviceUserName;
    private String sponsorID;
    private String applicationID;
    private String applicationName;
    private String applicationVersion;
    private String deviceIPAddress;
    private String applicationVersionCode;
    private String cbaProductName;
    private String appsDirectoryPath;
    private String deviceTimeZone;

    private ArrayList<String> list;
    private ArrayList<String> listOfAppName;
    private ArrayList<String> listOfDevices;
    private ArrayList<String> listOfBundles;
    private ArrayList<String> listOfApps;

//    private User user;

    public EnvConfig(String env, String portal) throws IOException {
        this.env = env;
        this.portal = portal;
        FileInputStream ip = new FileInputStream(java.nio.file.Paths.get(
                new File(System.getProperty("user.dir")).getParent(),
                "infra", "src", "main", "java", "com", "verifone", "infra",
                "config.properties").toString());
        prop.load(ip);
        setEnv();
    }


    private void setEnv() throws IOException {
        this.apiUrls = new ApiUrls(env, prop);
        this.credentials = new Credentials(env, prop);
        this.webUrl = prop.getProperty(portal + "." + env);
    }


    public ApiUrls getApiUrls() {
        return apiUrls;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getEnv() {
        return env;
    }

    public String getAppName() {
        return prop.getProperty(env + "." + "MPGetAppName");
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getOneTimeAppName() {
        return prop.getProperty(env + "." + "MPPurchaseOneTimeApp");
    }

    public String getFreeEditionApp() {
        return prop.getProperty(env + "." + "MPPurchaseFreeEditionApp");
    }

    public String getMonthlyRecurringApp() {
        return prop.getProperty(env + "." + "MPMonthlyRecurringApp");
    }

    public String getYearlyRecurringApp() {
        return prop.getProperty(env + "." + "MPYearlyRecurringApp");
    }

    public String getOneTimePayFreeTrialApp() {
        return prop.getProperty(env + "." + "MPPurchaseOneTimePayFreeTrialApp");
    }

    public String getMonthlyRecurringFreeTrialApp() {
        return prop.getProperty(env + "." + "MPMonthlyRecurringFreeTrialApp");
    }

    public String getYearlyRecurringFreeTrialApp() {
        return prop.getProperty(env + "." + "MPYearlyRecurringFreeTrialApp");
    }

    public String getCmFiveDeviceSerialNo01() {
        return prop.getProperty(env + "." + "CMFiveDeviceSerialNo01");
    }

    public String getDeviceUserName() {
        return prop.getProperty(env + "." + "MPDeviceUserName");
    }

    public String getSponsorID() {
        return prop.getProperty(env + "." + "SponsorID");
    }

    public String getApplicationID() {
        return prop.getProperty(env + "." + "ApplicationID");
    }

    public String getApplicationName() {
        return prop.getProperty(env + "." + "ApplicationName");
    }

    public String getApplicationVersion() {
        return prop.getProperty(env + "." + "ApplicationVersion");
    }

    public String getDeviceIPAddress() {
        return prop.getProperty(env + "." + "CMFiveDeviceIPAddress");
    }

//    public String getMPReseller() {
//        return prop.getProperty(env + "." + "MPReseller");
//    }

    public ArrayList<String> getList() {
        list = new ArrayList<>();
        list.add(prop.getProperty(env + "." + "MPFirstGroupName"));
        list.add(prop.getProperty(env + "." + "MPFirstGroupDescription"));
        return list;
    }

    public ArrayList<String> getListOfAppName() {
        listOfAppName = new ArrayList<>();
        listOfAppName.add(prop.getProperty(env + "." + "MPPurchaseFreeAppOne"));
        listOfAppName.add(prop.getProperty(env + "." + "MPPurchaseFreeAppFour"));
        return listOfAppName;
    }

    public ArrayList<String> getListOfDevices() {
        listOfDevices = new ArrayList<>();
        listOfDevices.add(prop.getProperty(env + "." + "CMFiveDeviceSerialNo02"));
        listOfDevices.add(prop.getProperty(env + "." + "CMFiveDeviceSerialNo01"));
        return listOfDevices;
    }

    public ArrayList<String> getListOfBundle() {
        listOfBundles = new ArrayList<>();
        listOfBundles.add(prop.getProperty(env + "." + "MPfirstBundleName"));
        listOfBundles.add(prop.getProperty(env + "." + "MPsecondBundleName"));
        return listOfBundles;
    }

    public ArrayList<String> getListOfAppforBundle() {
        listOfApps = new ArrayList<>();
        listOfApps.add(prop.getProperty(env + "." + "app1"));
        listOfApps.add(prop.getProperty(env + "." + "app2"));
        listOfApps.add(prop.getProperty(env + "." + "app3"));
        listOfApps.add(prop.getProperty(env + "." + "app4"));
        return listOfApps;
    }

    public String getApplicationVersionCode() {
        return prop.getProperty(env + "." + "ApplicationVersionCode");
    }

    public String getCbaProductName() {
        return prop.getProperty(env + "." + "CBAProductName");
    }

    public String getAppsDirectoryPath() {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "apps";
    }

    public String getDeviceTimeZone() {
        return prop.getProperty(env + "." + "CMDeviceTimeZone");
    }

    public String getDestinationFolder() {
        return prop.getProperty(env + "." + "destinationFolder");
    }



}
