package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class ReportsPage extends BasePage {

    private final static String url = "";
    private final static String title = "Reports Page";

    private By invoiceDetailesReport = By.xpath("//tr[contains(@class, 'TableContainer')][1]");
    private By transactionReport = By.xpath("//tr[contains(@class, 'TableContainer')][2]");
    private By paymentDetailesReport = By.xpath("//tr[contains(@class, 'TableContainer')][3]");
    private String editOption = "(//i[contains(@name, 'cog')])";
    private String downloadReport = "(//a[text()='Download'])";
    private String workingDir = System.getProperty("user.dir");
    private String downlodDir = workingDir + "\\src\\test\\resources\\reports";

    public ReportsPage() {
        super(url, title);
    }

    public void clickOptionButton(int rowNumber){
        By lineToEdit = By.xpath(editOption + "[" + rowNumber + "]");
        click(lineToEdit);
    }

    public void clickDownloadButton(int rowNumber){
        By reportToDownload = By.xpath(downloadReport + "[" + rowNumber + "]");
        click(reportToDownload);

    }

    public void downloadReport(int rowNumber){
        clickOptionButton(rowNumber);
        clickDownloadButton(rowNumber);
    }

}
