package com.naskoni.phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.naskoni.phonebook.interfaces.OutputWriter;

public class PhoneBook {

	private static final int ENTRIES_MOST_OUTGOING_CALLS = 5;
	private static final int MAX_CALLS = 30;

	private final Set<PhoneBookEntry> entries = new TreeSet<>();
	private final Set<CallLog> callLogs = new TreeSet<>();

	private OutputWriter outputWriter;

	public PhoneBook(OutputWriter outputWriter) {
		this.outputWriter = outputWriter;
	}

	public void loadEntries(String filePath) throws FileNotFoundException, IOException {
		File file = new File(filePath);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				String name = tokens[0];
				String number = tokens[1];
				if (PhoneBookEntry.isValidNumber(number.trim())) {
					addEntry(name, number);
				}
			}
		}
	}

	public void addEntry(String name, String number) {
		if (!PhoneBookEntry.isValidNumber(number.trim())) {
			throw new IllegalArgumentException("The number is not valid.");
		}

		PhoneBookEntry phoneBookEntry = new PhoneBookEntry(name, number);
		if (entries.contains(phoneBookEntry)) {
			String message = String.format("An entry with this name: [%s] already exist.%n", name);
			outputWriter.printline(message);
			return;
		}

		entries.add(phoneBookEntry);
	}

	public boolean deleteEntry(String name) {
		if (entries.removeIf(e -> name.equals(e.getName()))) {
			return true;
		}

		return false;
	}

	public String findNumber(String name) {
		return entries.stream().filter(e -> name.equals(e.getName())).findFirst().get().getNumber();
	}

	public void printEntries() {
		for (PhoneBookEntry entry : entries) {
			outputWriter.printline(entry);
		}
	}

	public void printMostOutgoingCalls() {
		int counter = 0;
		for (CallLog callLog : callLogs) {
			outputWriter.printline(callLog);
			if (++counter == ENTRIES_MOST_OUTGOING_CALLS) {
				break;
			}
		}
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
}
