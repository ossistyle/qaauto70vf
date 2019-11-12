package com.verifone.tests.cgTests;

import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import com.verifone.utils.SshUtils.SshCli;
import org.testng.Assert;

import static com.verifone.pages.BasePage.testLog;

public class ManualTransactionSSH extends BaseTest {

    @Test(testName = "ManualTransactionSSH", description = "Manual Transaction CG via SSH", groups = {"CG-Portal"})
        public void  ManualTransactionSSH() throws Exception {
        SshCli sshCli = new SshCli();
        String cliResult1 = null;
//
        {
         sshCli.execSshCli("10.160.35.31", "root", "p@yware", "date", Boolean.TRUE);
         cliResult1 = sshCli.execSshCli("10.160.35.31", "root", "p@yware",
                "cd /usr/share/httest/httest-2.4.12-linux-64/testList/Active_On_Status_Test ; httest 1_ActiveOn.Htt | grep 200 | awk '{print $2,$3}'"
                 , Boolean.TRUE);
        }

        // checking the cli results
        Assert.assertTrue(cliResult1.contains("200"));

            }
        }





