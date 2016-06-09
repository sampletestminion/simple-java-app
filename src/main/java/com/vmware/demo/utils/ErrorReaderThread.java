/* **********************************************************
 * Copyright 2010 VMware, Inc.  All rights reserved. -- VMware Confidential
 * **********************************************************/
package com.vmware.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ErrorReaderThread extends Thread {

    //private static final Logger logger = LoggerFactory.getLogger(ErrorReaderThread.class);

    private final InputStream errInputStream;
    private StringBuilder outputsb = new StringBuilder();

    public ErrorReaderThread(Process process) {
        errInputStream = process.getErrorStream();
    }

    @Override
    public void run() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(errInputStream));
        String output = null;
        try {
            while ((output = reader.readLine()) != null) {
                //append the line in case it is needed later on
                outputsb.append(output).append("\n");
                System.err.println("ERR:"+output);
            }
        } catch (IOException e) {
            System.err.println("Exception while reading process stderr : " + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * @return a single string with all output directed to stderr.  Will includes \n chars for lines.
     */
    public String getOutput() {
        return outputsb.toString();
    }
}
