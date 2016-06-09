/* **********************************************************
 * Copyright 2010-2014 VMware, Inc.  All rights reserved.
 * **********************************************************/
package com.vmware.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * simple utility class to read the output of a Stream to a String.  It reads a line at a time
 * from the stream and for read one that it gets, it appends that line to a StringBuffer internally
 * and then you can call to get the String at any point.  Typically you would want to call join on
 * the thread so that you were sure that all output was in there and the  thread had completed.
 * @author aspear
 *
 */
public class ReadStreamToStringThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ReadStreamToStringThread.class);

    private final InputStream inputStream;
    private List<String> outputList = new ArrayList<String>(8); // using StringBuffer since it is thread safe

    public ReadStreamToStringThread(String name, InputStream inputStream) {
        super(name);
        this.inputStream = inputStream;
    }

    @Override
    public void run() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String output = null;
        try {
            while ((output = reader.readLine()) != null) {
                outputList.add(output.trim());
            }
        } catch (Exception e) {
            logger.error("Exception while reading process output",e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Get all lines written to the stream split into a list of strings.
     * Note that all leading and trailing whitespace has been removed from
     * all lines (they have been trimmed).
     * @return list of all output lines. Could be empty if nothing was written, but never null.
     * Note that this is the list that is used by the thread.  So, if you get the object while the
     * thread is running, it could still be changing as strings are inserted.  As such, you should most
     * certainly call "join" on the thread before using it.
     */
    public List<String> getOutput() {
        return outputList;
    }

    /**
     * convenience method that gets all output as a single string
     * @return
     */
    public String getAllOutput() {
        StringBuilder sb = new StringBuilder();
        for (String s : outputList) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }
}
