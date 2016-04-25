package com.musala.phonebook;

public class PhoneBookEntry implements Comparable<PhoneBookEntry> {

	private String name;
	private String number;

	public PhoneBookEntry(String name, String number) {
		if (name == null || name.trim().isEmpty()) {
			System.out.println("The name cannot be null or empty.");
		}

		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return name + " - " + number;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof PhoneBookEntry) {
			PhoneBookEntry phoneBookEntry = (PhoneBookEntry) obj;
			return name.equals(phoneBookEntry.getName()) && number.equals(phoneBookEntry.getNumber());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode() * number.hashCode();
	}

	@Override
	public int compareTo(PhoneBookEntry phoneBookEntry) {
		return name.compareTo(phoneBookEntry.getName());
	}
}
