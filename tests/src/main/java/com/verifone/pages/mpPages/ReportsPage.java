package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ReportsPage extends BasePage {

    private final static String url = "";
    private final static String title = "Reports Page";

    private String reportRow = "(//td[contains(@class, 'TableContainer')])";
    private String editOption = "(//i[contains(@name, 'cog')])";
    private String downloadReport = "(//a[text()='Download'])";

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
        try{
            Thread.sleep(4000);
        }
        catch(Exception e){
            System.out.println("Oops " + e);
        }

    }

    public String downloadReport(int rowNumber){
        String creationTime = reportWasCreated(rowNumber);
        String reportName = reportName(rowNumber);
        clickOptionButton(rowNumber);
        clickDownloadButton(rowNumber);
        return (reportName + " that was created on " + creationTime +" downloaded") ;
    }

    public String reportWasCreated(int rowNumber){
        int algo = 3 +(rowNumber * 9);
        String rowNum = reportRow + "[" + algo + "]";
        WebElement timeStamp = driver.findElement(By.xpath(rowNum));
        return timeStamp.getText();
    }

    public String reportName(int rowNumber){
        int algo = 5 +(rowNumber * 9);
        String rowNum = reportRow + "[" + algo + "]";
        WebElement timeStamp = driver.findElement(By.xpath(rowNum));
        return timeStamp.getText();
    }


}
