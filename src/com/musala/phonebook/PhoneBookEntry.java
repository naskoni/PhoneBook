package com.musala.phonebook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookEntry implements Comparable<PhoneBookEntry> {

	private static final Pattern pattern = Pattern.compile("^(\\+359|0|00359)8[7-9][2-9]\\d{6}$");

	private String name;
	private String number;

	public PhoneBookEntry(String name, String number) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("The name cannot be null or empty.");
		}

		if (!PhoneBookEntry.isValidNumber(number.trim())) {
			throw new IllegalArgumentException("The number is not valid.");
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

	public static boolean isValidNumber(String number) {
		Matcher matcher = pattern.matcher(number);
		if (matcher.matches()) {
			return true;
		}

		return false;
	}
}