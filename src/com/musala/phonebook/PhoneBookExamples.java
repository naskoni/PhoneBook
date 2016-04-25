package com.musala.phonebook;

public class PhoneBookExamples {

	private PhoneBookExamples() {
	}

	public static void main(String[] args) {
		PhoneBook phoneBook = new PhoneBook();

		phoneBook.loadEntries();
		phoneBook.printEntries();

		phoneBook.addEntry("Spiro", "0880888488");
		phoneBook.addEntry("Atanas", "0897999061");
		phoneBook.addEntry("Kiril", "0876543717");
		phoneBook.addEntry("Spiro", "0888888388");
		phoneBook.addEntry("Atanaska", "0897999062");
		phoneBook.printEntries();

		phoneBook.deleteEntry("Spiro");
		phoneBook.printEntries();

		System.out.println(phoneBook.findNumber("Kiril"));

		phoneBook.generateCalls();
		phoneBook.printMostOutgoingCalls();
	}
}
