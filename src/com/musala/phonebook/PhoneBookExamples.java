package com.musala.phonebook;

import java.io.IOException;

import com.musala.phonebook.io.ConsoleWriter;

public class PhoneBookExamples {

	private static final String FILE_PATH = "resources/phonebook.txt";

	private PhoneBookExamples() {
	}

	public static void main(String[] args) {
		PhoneBook phoneBook = new PhoneBook(new ConsoleWriter());

		try {
			phoneBook.loadEntries(FILE_PATH);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		phoneBook.printEntries();
		printSeparatorLine();

		phoneBook.addEntry("Spiro", "0888388488");
		phoneBook.addEntry("Kiril", "0876543717");
		phoneBook.addEntry("Radko", "0888888388");
		phoneBook.addEntry("Atanaska", "0897999062");

		// uncomment to try add an invalid number:
		// phoneBook.addEntry("Atanas", "0890999061");

		phoneBook.printEntries();
		printSeparatorLine();

		String nameToDelete = "Spiro";
		if (phoneBook.deleteEntry(nameToDelete)) {
			System.out.printf("The entry with this name: [%s] was deleted.%n", nameToDelete);
		} else {
			System.out.printf("The entry with this name: [%s] does not exist.%n", nameToDelete);
		}

		printSeparatorLine();
		phoneBook.printEntries();
		printSeparatorLine();

		System.out.println("The number of Kiril: " + phoneBook.findNumber("Kiril"));
		printSeparatorLine();

		phoneBook.generateCalls();
		System.out.println("5 contacts with most outgoing calls:");
		phoneBook.printMostOutgoingCalls();
	}

	private static void printSeparatorLine() {
		System.out.println("--------------------");
	}
}
