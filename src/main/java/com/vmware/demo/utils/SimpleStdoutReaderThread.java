/* **********************************************************
 * Copyright 2010 VMware, Inc.  All rights reserved. -- VMware Confidential
 * **********************************************************/
package com.vmware.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SimpleStdoutReaderThread extends Thread {

    //private static final Logger logger = LoggerFactory.getLogger(ErrorReaderThread.class);

    private final InputStream outputStream;

    public SimpleStdoutReaderThread(Process process) {
        outputStream = process.getInputStream();
    }

    @Override
    public void run() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(outputStream));
        String output = null;
        try {
            while ((output = reader.readLine()) != null) {
                System.out.println("OUT:"+output);
            }
        } catch (IOException e) {
            System.err.println("Exception while reading process stdout : " + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }
}