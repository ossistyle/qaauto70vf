package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MerchantDetailsPage extends BasePage {


        private final static String url = "";
        private final static String title = "";

        private By titleLoc = By.xpath("//*[@class='top-container']");
        private By busNameLoc = By.xpath("(//*[@class='control-value'])[1]");
        private By indCodeLoc = By.xpath("(//*[@class='control-value'])[2]");
        private By midLoc = By.xpath("(//*[@class='control-value'])[3]");
        private By countryLoc = By.xpath("(//*[@class='control-value'])[4]");
        private By addressLoc = By.xpath("(//*[@class='control-value'])[5]");
        private By contNumberLoc = By.xpath("(//*[@class='control-value'])[6]");
        private By busMailLoc = By.xpath("(//*[@class='control-value'])[8]");

        private By merchantEmailLoc = By.xpath("(//*[@class='control-value'])[11]");
        private By businessEditLoc = By.xpath("//*[@class='edit-organization pull-right']");
        private By businessEditMutedLoc = By.xpath("(//*[@class='pull-right text-muted'])[1]");
        private By userEditLoc = By.xpath("(//*[@class='pull-right text-muted'])[1]");
        private By roleEditLoc = By.xpath("(//*[@class='pull-right text-muted'])[2]");
        private By userEnableEditLoc = By.xpath("//*[@class='pull-right edit-user']");
        private By roleEnableEditLoc = By.xpath("//*[@class='pull-right edit-role']");
        private By userStatusLoc = By.xpath("//*[@class='label label-info']");
        private By userActiveStatusLoc = By.xpath("//*[@class='label label-success']");
        private By userDisabledStatusLoc = By.xpath("//*[@class='label label-default']");
        private By userActionLoc = By.xpath("//*[@class='action']");
        private By roleLoc = By.xpath("(//*[@class='control-value'])[4]");
        private By lnkResendLoc = By.xpath("//*[@class='resend-invitation']");
        private By lnkDisableLoc = By.xpath("//*[@class='disable-user']");
        private By lnkEnableLoc = By.xpath("//*[@class='enable-user']");
        private By messageLoc = By.xpath("//*[@class='message success']");

        private By dialogResendLoc = By.xpath("(//*[@class='modal-dialog  '])[3]");
        private By dialogResendTextLoc = By.xpath("(//*[@class='modal-body scroll'])[3]");
        private By dialogCancelBtnLoc = By.id("resendEmailModalNegateId");
        private By dialogResendBtnLoc = By.id("resendEmailModalAffirmId");

        private By dialogDisableLoc = By.xpath("(//*[@class='modal-dialog  '])[1]");
        private By dialogDisableTextLoc = By.xpath("(//*[@class='modal-body scroll'])[1]");
        private By dialogDisableCancelBtnLoc = By.id("disableUserModalNegateId");
        private By dialogDisableBtnLoc = By.id("disableUserModalAffirmId");

        private By dialogEnableLoc = By.xpath("(//*[@class='modal-dialog  '])[2]");
        private By dialogEnableTextLoc = By.xpath("(//*[@class='modal-body scroll'])[2]");
        private By dialogEnableCancelBtnLoc = By.id("enableUserModalNegateId");
        private By dialogEnableBtnLoc = By.id("enableUserModalAffirmId");

        public MerchantDetailsPage() {
            super(url, title);
        }

        public String getMerchantEmail() throws InterruptedException {
            scrollToElement(merchantEmailLoc);
            Thread.sleep(2000);
            String a = getText(merchantEmailLoc);
            return a;
        }

        public String getBusName() throws InterruptedException {
            int k = 0;
            while (getText(busNameLoc)=="" & k <=100){
                Thread.sleep (1000);
                k++;
            }
            return getText(busNameLoc);
        }

        public String getTitle() throws InterruptedException {
            return getText(titleLoc);
        }

        public String getIndCode() throws InterruptedException {
            return getText(indCodeLoc);
        }

        public String getMID() throws InterruptedException {
            return getText(midLoc);
        }

        public String getCountry() throws InterruptedException {
            return getText(countryLoc);
        }

        public String getAddress() throws InterruptedException {
            return getText(addressLoc);
        }

        public String getContactNumber() throws InterruptedException {
            return getText(contNumberLoc);
        }

        public String getBusEmail() throws InterruptedException {
            return getText(busMailLoc);
        }

        public String getStatus() throws Exception {
            if (isExists(userStatusLoc, 3)) {
                return getText(userStatusLoc);
            }
            else if (isExists(userActiveStatusLoc, 3)) {
                return getText(userActiveStatusLoc);
            }
            else if (isExists(userDisabledStatusLoc, 3)) {
                return getText(userDisabledStatusLoc);
            }
            return "";
        }

        public String getAction() throws InterruptedException {
            return getText(userActionLoc);
        }

        public String getRole() throws InterruptedException {
            return getText(roleLoc);
        }

        public String getDialogResend() throws InterruptedException {
            return getText(dialogResendTextLoc);
        }

        public String getDialogDisable() throws InterruptedException {
            return getText(dialogDisableTextLoc);
        }

        public String getDialogEnable() throws InterruptedException {
            return getText(dialogEnableTextLoc);
        }

        public boolean elementEmailClickable() throws InterruptedException {
            WebElement elem = driver.findElement(merchantEmailLoc);
//    	return elem.isEnabled();
            try{
                WebDriverWait wait = new WebDriverWait(driver, 6);
                wait.until(ExpectedConditions.attributeContains(elem, "localName", "input"));
                return true;
            }
            catch (Exception e){
                return false;
            }
        }

        public boolean elementRoleClickable() throws InterruptedException {
            WebElement elem = driver.findElement(roleLoc);
//    	return elem.isEnabled();
            try{
                WebDriverWait wait = new WebDriverWait(driver, 6);
                wait.until(ExpectedConditions.attributeContains(elem, "localName", "input"));
                return true;
            }
            catch (Exception e){
                return false;
            }
        }

        public boolean elementBusinessEditClickable() throws Exception {

            if (isExists(businessEditMutedLoc, 3)) {
                return false;
            }
            if (isExists(businessEditLoc, 3)) {
                return true;
            }
            return false;
        }

        public boolean elementRoleEditClickable() throws Exception {
            if (isExists(roleEditLoc, 3)) {
                return false;
            }
            if (isExists(roleEnableEditLoc, 3)) {
                return true;
            }
            return false;
        }

        public void clickLnkEditBus() throws InterruptedException {
            click(businessEditLoc);
        }

        public void clickLnkResend() throws InterruptedException {
            click(lnkResendLoc);
        }

        public void clickLnkDisable() throws InterruptedException {
            click(lnkDisableLoc);
        }

        public void clickLnkEnable() throws InterruptedException {
            click(lnkEnableLoc);
        }

        public boolean dialogResendExists() throws Exception {
            return isExists(dialogResendLoc, 3);
        }
        public void clickCancelResend() throws InterruptedException {
            click(dialogCancelBtnLoc);
        }
        public void clickDoResend() throws InterruptedException {
            click(dialogResendBtnLoc);
        }
        public boolean dialogDisableExists() throws Exception {
            return isExists(dialogDisableLoc, 3);
        }
        public void clickCancelDisable() throws InterruptedException {
            click(dialogDisableCancelBtnLoc);
        }
        public void clickDoDisable() throws InterruptedException {
            click(dialogDisableBtnLoc);
        }

        public boolean dialogEnableExists() throws Exception {
            return isExists(dialogEnableLoc, 3);
        }
        public void clickCancelEnable() throws InterruptedException {
            click(dialogEnableCancelBtnLoc);
        }
        public void clickDoEnable() throws InterruptedException {
            click(dialogEnableBtnLoc);
        }

        public boolean messageExists() throws Exception {
            return isExists(messageLoc, 30);
        }
        public String getMessage() throws InterruptedException {
            return getText(messageLoc);
        }


}