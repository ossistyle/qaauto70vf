package com.verifone.utils.SshUtils;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import static com.verifone.pages.BasePage.testLog;

/**
 * This class contains convenience methods for working with JSch SSH Connection
 *
 * @Giora Tovim
 */

public class SshCli {


    public String execSshCli(String host, String user, String password , String command, Boolean printCliResult ) throws Exception {

        try {

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            testLog.info("Connected");
            System.out.println("Connected");

            testLog.info("print Cli Result is: " + printCliResult);
            System.out.println("Print Cli Result is: " + printCliResult);

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    else {
                        String cliResult = new String(tmp, 0, i);
                        if (printCliResult) {
                            testLog.info("Exec CLI command:");
                            testLog.info(command);
                            testLog.info("CLI Results is:");
                            testLog.info(cliResult);
                            System.out.println("Exec CLI command:");
                            System.out.println(command);
                            System.out.println("CLI Results is:");
                            System.out.println(cliResult);
                        }

                        testLog.info("Done - Disconnect");
                        System.out.println("Done - Disconnect");
                        channel.disconnect();
                        session.disconnect();
                        return cliResult;
                    }
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            System.out.println("Done - disconnect");
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

