package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
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
    private static final String androidProjectPath = BaseTest.envConfig.getAppsDirectoryPath() + File.separator + "MyApplication164";
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
        testLog.info("<b><u>Update androidManifest.xml file</u></b>");
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
            testLog.info("<b><u>androidManifest.xml :</u></b> file updated with application id " + androidProjectAppId);
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
        testLog.info("<b><u>Update string.xml file</u></b>");

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
            testLog.info("<b><u>String.xml :</u></b> file updated with application name " + androidProjectAppName);

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
        UUID uuid = UUID.randomUUID();
        int index = uuid.toString().lastIndexOf("-");
        if (index >= 0) {
            androidProjectAppName = androidProjectAppName + uuid.toString().substring(index + 1, index + 3); //index + 1, index + 10
        }
        testLog.info("<b>Application Name :<u>" + androidProjectAppName + "<u></b>");
        return androidProjectAppName;
    }

    /**
     * This method generate app id using the app name and random numbers
     * 24/10/2019
     *
     * @author : Prashant Lokhande
     */
    public String getAndroidAppId() {
        System.out.println("App id :" + androidProjectAppName + "-" + RandomStringUtils.randomNumeric(9));
        androidProjectAppId = androidProjectAppName + "-" + RandomStringUtils.randomNumeric(9);
        testLog.info("<b>AppId :<u>" + androidProjectAppId + "<u></b>");
        return androidProjectAppId;
    }

    /**
     * This method generate apk
     * 24/10/2019
     *
     * @author : Prashant Lokhande
     */

    public void generateAndroidAPK() {

        String[] cmds = {"gradlew.bat", androidProjectPath};

        Runtime runtime = Runtime.getRuntime();
        String line = "";
        BufferedReader bufferedreader = null;
        StringBuilder ouput = new StringBuilder();
        InputStream inputstream = null;
        InputStreamReader inputstreamreader = null;
        Process proc = null;

        File apkFile = new File(androidAPKPath);
        if (isFileExists(apkFile, 10)) {
            apkFile.delete();
        }


        try {
            // proc = runtime.exec(cmds, null, new File(batchFilePath));
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "gradlew build");
            File dir = new File(androidProjectPath);
            pb.directory(dir);
            proc = pb.start();
            boolean isExited = proc.waitFor(10000, TimeUnit.MILLISECONDS);

            if (isExited) {
                int exitValue = proc.exitValue();
                System.out.println("exitValue " + exitValue);
//            System.out.println(" String.valueOf(System.in) " + new InputStreamReader(System.in).getEncoding());
                System.out.println("proc alive ?  " + proc.isAlive());
                if (exitValue == 0) {
                    inputstream = proc.getInputStream();
                    inputstreamreader = new InputStreamReader(inputstream);
                    bufferedreader = new BufferedReader(inputstreamreader);
                } else {
                    inputstream = proc.getErrorStream();
                    inputstreamreader = new InputStreamReader(inputstream);
                    bufferedreader = new BufferedReader(inputstreamreader);
                }


                while ((line = bufferedreader.readLine()) != null) {
                    System.out.println("length " + line.length());
                    ouput.append(line + "<br>" + "\n");
                }

                if (isFileExists(apkFile, 180)) {
                    testLog.info("<b>Info -> </b> APK is generated.");
                } else {
                    testLog.info("<b>Info -> </b> Apk takes too much time to generate.");
                    Assert.fail("<b>Info -> </b> Apk takes too much time to generate.");
                }

            } else {

                if (isFileExists(apkFile, 200)) {
                    testLog.info("<b>Info -> </b> APK is generated.");
                } else {
                    testLog.info("<b>Info -> </b> Apk takes too much time to generate.");
                    Assert.fail("<b>Info -> </b> Apk takes too much time to generate.");
                }

                if (proc.isAlive()) {
                    proc.destroy();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (inputstream != null) {
                    inputstream.close();
                }

                if (inputstreamreader != null) {
                    inputstreamreader.close();
                }

                if (bufferedreader != null) {
                    bufferedreader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
