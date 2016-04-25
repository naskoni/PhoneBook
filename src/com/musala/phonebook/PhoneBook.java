package com.musala.phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

	private static final String PHONE_REGEX = "^(\\+359|0|00359)8[7-9][2-9]\\d{6}$";
	private static final int ENTRIES_MOST_OUTGOING_CALLS = 5;
	private static final int MAX_CALLS = 30;

	private static final Set<PhoneBookEntry> entries = new TreeSet<>();
	private static final Set<CallLog> callLogs = new TreeSet<>();

	public void loadEntries() {
		File file = new File("resources/phonebook.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				String name = tokens[0];
				String number = tokens[1];

				addEntry(name, number);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found or cannot be opened.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addEntry(String name, String number) {
		if (!isValidNumber(number.trim())) {
			System.out.printf("The number: [%s] associated with name: [%s] is not valid.%n", number, name);
			return;
		}

		PhoneBookEntry phoneBookEntry = new PhoneBookEntry(name, number);
		if (entries.contains(phoneBookEntry)) {
			System.out.printf("An entry with this name: [%s] already exist.%n", name);
			return;
		}

		entries.add(new PhoneBookEntry(name, number));
	}

	public void deleteEntry(String name) {
		if (entries.removeIf(e -> name.equals(e.getName()))) {
			System.out.printf("The entry with this name: [%s] was deleted.%n", name);
		} else {
			System.out.printf("The entry with this name: [%s] does not exist.%n", name);
		}
	}

	public String findNumber(String name) {
		return entries.stream().filter(e -> name.equals(e.getName())).findFirst().get().getNumber();
	}

	public void printEntries() {
		printSeparatorLine();
		for (PhoneBookEntry entry : entries) {
			System.out.println(entry);
		}

		printSeparatorLine();
	}

	public void printMostOutgoingCalls() {
		printSeparatorLine();
		int counter = 0;
		for (CallLog callLog : callLogs) {
			System.out.println(callLog);
			if (++counter == ENTRIES_MOST_OUTGOING_CALLS) {
				break;
			}
		}

		printSeparatorLine();
	}

	public void generateCalls() {
		Random randomGenerator = new Random();
		for (PhoneBookEntry entry : entries) {
			CallLog callLog = new CallLog(entry);
			int randomNumber = randomGenerator.nextInt(MAX_CALLS);
			callLog.addCalls(randomNumber);
			callLogs.add(callLog);
		}
	}

	private void printSeparatorLine() {
		System.out.println("--------------------");
	}

	private boolean isValidNumber(String number) {
		Pattern pattern = Pattern.compile(PHONE_REGEX);
		Matcher matcher = pattern.matcher(number);

		if (matcher.matches()) {
			return true;
		}

		return false;
	}
}
