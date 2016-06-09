package com.vmware.demo.rpm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestApp1 {
    public static volatile boolean done = false;

    public static void main(String[] args) {
		System.out.println("TestApp1 starting up.");

		if (args.length != 2) {
		    System.err.println("you must supply a command as first arg and path as second arg");
		    return;
		}

		String command = args[0].trim();
		String dir = args[1].trim();

		System.out.println("arg0='" + command + "' arg1='" + dir + "'");

	    File rpmDir = new File(dir);


	}
}
