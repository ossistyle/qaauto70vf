package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.testng.Assert;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Created by admin on 1/5/2018.
 */
public class AndroidProjectOperationPage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    public static String androidProjectAppName = "CBATest";
    public static String androidProjectAppId = "";
    private static final String androidManifestPath = File.separator + "src" + File.separator + "main" + File.separator + "AndroidManifest.xml";
    private static final String androidProjectPath = BaseTest.envConfig.getAppsDirectoryPath() + File.separator + "apps" + File.separator + "TestAPK";
    private static final String androidAPKPath = androidProjectPath + File.separator + "app" + File.separator + "build" + File.separator + "outputs" + File.separator + "apk" + File.separator + "debug" + File.separator + "app-debug.apk";

    public AndroidProjectOperationPage() {
        super(url, title);
    }

    /**
     * This method update the app id in the android manifest xml file
     * 24/10/2019
     *
     * @author : Prashant Lokhande
     */

    public void updateXMLFileWithAppId() {
        testLog.info("------------------------------------- Update androidManifest.xml file -------------------------------------");
        try {
            String manifestAppId = getAndroidAppId();
            System.out.println("copy CopyAppid2AndroidManifestXmlFile" + manifestAppId);
            File androidMFTXML = new File
                    (androidProjectPath + File.separator + "app" + androidManifestPath);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(androidMFTXML);

            NodeList nodeList = doc.getElementsByTagName
                    ("application");
            NodeList nodeListM = doc.getElementsByTagName
                    ("meta-data");
            Node node = null;
            boolean isNodePresent = false;
            int nodeLevel = 0;
            for (int i = 0; i < nodeListM.getLength(); i++) {
                node = nodeListM.item(i);
                String valueNode = node.getAttributes().getNamedItem
                        ("android:name").getNodeValue();

                if ("com.verifone.commerce.manifest.appid".equalsIgnoreCase
                        (valueNode.trim())) {
                    isNodePresent = true;
                    nodeLevel = i;
                    break;
                }
            }

            System.out.println("isNodePresent " + isNodePresent);
            if (isNodePresent) {
                node = nodeListM.item(nodeLevel);
                NamedNodeMap attr = node.getAttributes();
                Node nodeAttr = attr.getNamedItem("android:value");
//                nodeAttr.setTextContent(manifestAppId);
                nodeAttr.setNodeValue(manifestAppId);
                System.out.println("nodeAttr " + nodeAttr.getNodeValue() + "id" + manifestAppId);
            } else {
                System.out.println("isNodePresent nt ");
                Node metaData = doc.createElement("meta-data");
                ((Element) metaData).setAttribute("android:name", "com.verifone.commerce.manifest.appid");
                ((Element) metaData).setAttribute("android:value", manifestAppId);
                nodeList.item(0).appendChild(metaData);
                System.out.println("nodelist " + nodeList.toString());
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String xmlString = result.getWriter().toString();
            System.out.println("xmlString " + xmlString + isNodePresent);
            if (!isNodePresent) {
                System.out.println("inside !isNodePresent  " + xmlString + " " + manifestAppId);

            }
            FileUtils.writeStringToFile(androidMFTXML, xmlString, Charset.defaultCharset());
            testLog.info("------------------------------------- androidManifest.xml : File updated with application id " + androidProjectAppId + " -------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method update the app name in string.xml file
     * 24/10/2019
     *
     * @author : Prashant Lokhande
     */

    public void updateXMLFileWithAppName() {
        testLog.info("------------------------------------- Update string.xml file -------------------------------------");

        File project = new File(androidProjectPath);
        String manifestAppName = getAndroidAppName();
        File stringMFTXML = new File
                (project + File.separator + "app" + File.separator + "src" +
                        File.separator + "main" + File.separator + "res" + File.separator + "values" + File.separator + "strings.xml");


        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(stringMFTXML);

            Node nodeList = doc.getElementsByTagName("string").item(0);

            if ("string".equals(nodeList.getNodeName())) {
                nodeList.setTextContent(manifestAppName);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            String xmlString = result.getWriter().toString();
            FileWriter writer = new FileWriter(stringMFTXML);
            writer.write(xmlString);
            writer.close();

            FileUtils.writeStringToFile(stringMFTXML, xmlString, Charset.defaultCharset());
            testLog.info("------------------------------------- String.xml : File updated with application name " + androidProjectAppName + " -------------------------------------");

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method generate app name using the app name using the uuid
     * 24/10/2019
     *
     * @author : Prashant Lokhande
     */
    public String getAndroidAppName() {
        testLog.info("------------------------------------- Generate Application Name -------------------------------------");
        UUID uuid = UUID.randomUUID();
        int index = uuid.toString().lastIndexOf("-");
        if (index >= 0) {
            androidProjectAppName = androidProjectAppName + uuid.toString().substring(index + 1, index + 3); //index + 1, index + 10
        }
        return androidProjectAppName;
    }

    /**
     * This method generate app id using the app name and random numbers
     * 24/10/2019
     *
     * @author : Prashant Lokhande
     */
    public String getAndroidAppId() {
        testLog.info("------------------------------------- Generate Application ID -------------------------------------");
        System.out.println("App id :" + androidProjectAppName + "-" + RandomStringUtils.randomNumeric(9));
        androidProjectAppId = androidProjectAppName + "-" + RandomStringUtils.randomNumeric(9);
        return androidProjectAppId;
    }

    /**
     * This method generate apk
     * 24/10/2019
     *
     * @author : Prashant Lokhande
     */

    public void generateAndroidAPK() {

        File apkFile = new File(androidAPKPath);
        if (isFileExists(apkFile, 10)) {
            apkFile.delete();
        }

        try {

            testLog.info("------------------------------------- Start APK Generation Process -------------------------------------");
            File dir = new File(androidProjectPath);

            //Verify the OS is Windows or Linux
            if (SystemUtils.IS_OS_WINDOWS) {
                testLog.info("------------------------------------- OS: Windows -------------------------------------");
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "gradlew build");
                pb.directory(dir);
                pb.start();
            } else {
                testLog.info("------------------------------------- OS: Linux -------------------------------------");
                File gradlewFile = new File(dir.getAbsolutePath() + File.separator + "gradlew");
                dir.setExecutable(true);
                gradlewFile.setExecutable(true);
                Runtime.getRuntime().exec("./gradlew build", null, dir);
            }

            //ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "gradlew build");
            //File dir = new File(androidProjectPath);
            //pb.directory(dir);
            // pb.start();

            if (isFileExists(apkFile, 300)) {
                testLog.info("------------------------------------- Test Passed : APK Generated Successfully. -------------------------------------");
            } else {
                Assert.fail("------------------------------------- Test Failed : APK generation process takes too much time to generate.  -------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
