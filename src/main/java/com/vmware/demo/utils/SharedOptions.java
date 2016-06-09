package com.vmware.demo.utils;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class SharedOptions {

	private Options options;
	private CommandLine commandLine;
	private String host = "https://dc-prod-repo1.vmware.com:8443/sampleExchange/v1";
	private String user;
	private String password;
	private boolean verbose = false;
	private String command = null;

	public SharedOptions() {
		createCommandLineOptions();
	}

	private void createCommandLineOptions() {
		options = new Options();

		// create the Options
		options.addOption(null, "user", true, "User name to be used when contacting the web service.");
		options.addOption(null, "password", true,
				"The specified users password to use for authentication with the web service.  Default is null");
		options.addOption(null, "host", true, "Full URL of the sample exchange server to connect to. ");
		options.addOption(null, "verbose", false, "If supplied, do verbose dumping of information.");
	}

	/**
	 * derived app can call to get options for the command line
	 *
	 * @return
	 */
	public Options getOptions() {
		return options;
	}

	public String[] parseArguments(String[] args) throws ParseException {
		// we have a command as the first arg always.
        if (args.length > 0) {
            command = args[0].toLowerCase();
            if (args.length > 1) {
                args = Arrays.copyOfRange(args, 1,args.length);
            }
        }

		// create the command line parser
		CommandLineParser parser = new PosixParser();

		// parse the command line arguments
		CommandLine commandLine = parser.parse(options, args);

		String[] parsedArgs = commandLine.getArgs();

		verbose = commandLine.hasOption("verbose");

		if (commandLine.hasOption("host")) {
			host = commandLine.getOptionValue("host");
		}
		if (commandLine.hasOption("user")) {
			user = commandLine.getOptionValue("user");
		}
		if (commandLine.hasOption("password")) {
			password = commandLine.getOptionValue("password");
		}
		return parsedArgs;
	}

	public void displayHelp(String appName) {

		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(appName, options);
	}

	public CommandLine getCommandLine() {
		return commandLine;
	}

	public String getHost() {
		return host;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public String getCommand() {
		return command;
	}
}
