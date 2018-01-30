package com.skilrock.utils;

import java.io.IOException;

public class DriverFactory {
	static Process process;

	/**
	 * This method Is responsible for starting appium server on windows.
	 */
	public static void appiumStartWindows() {
		String[] command = { "cmd.exe", "/C", "Start", "appium" };

		ProcessBuilder pb;
		if (process == null) {
			pb = new ProcessBuilder(command);
			try {
				process = pb.start();
				Thread.sleep(20000);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if (process != null) {
			System.out.println("SERVER STARTED");
		}
	}

	/**
	 * This method Is responsible for starting appium server on mac.
	 */
	public static void appiumStartMac() {
		String[] command = { "/usr/bin/osascript", "-e", "tell app \"Terminal\"", "-e",
				"set currentTab to do script " + "(\"appium\")", "-e", "end tell" };
		final ProcessBuilder processBuilder;
		if (process == null) {
			try {
				processBuilder = new ProcessBuilder(command);
				process = processBuilder.start();
				process.waitFor();
				Thread.sleep(20000);

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (process != null) {
			System.out.println("SERVER STARTED");
		}
	}

	/**
	 * This method Is responsible for stopping appium server on windows.
	 */
	public static void appiumStop() {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "taskkill /f /im " + "node.exe");
		builder.redirectErrorStream(true);
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("TASK KILLED");
	}

}
