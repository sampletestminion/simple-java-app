package com.vmware.demo.other.apps;

import com.vmware.demo.utils.SharedOptions;

public class TestApp3 {

	private static final String APP_NAME = "TestApp3";


    public static void main(String[] args) throws Exception {

        // instantiate shared options
    	SharedOptions sharedOptions = new SharedOptions();

        String[] parsedArgs = sharedOptions.parseArguments(args);

        System.out.println("App " + APP_NAME + " starting up...");
        System.out.println("Connecting to host='" + sharedOptions.getHost() + "' user='" + sharedOptions.getUser() + "'.'");

        System.out.println("Executing  command " + sharedOptions.getCommand());

        // we don't really do anything...
        int index=0;
        for (String arg : parsedArgs) {
        	System.out.printf("arg%d=%s\n",index++,arg);
        }

        System.out.println(APP_NAME + " Finished!");
	}
}
