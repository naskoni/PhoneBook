package com.naskoni.phonebook;

public class CallLog implements Comparable<CallLog> {

	private PhoneBookEntry phoneBookEntry;
	private int callsCounter;

	public CallLog(PhoneBookEntry phoneBookEntry) {
		this.phoneBookEntry = phoneBookEntry;
	}

	public PhoneBookEntry getPhoneBookEntry() {
		return phoneBookEntry;
	}

	public int getCallsCounter() {
		return callsCounter;
	}

	public void addCalls(int callsMade) {
		this.callsCounter += callsMade;
	}

	@Override
	public String toString() {
		return phoneBookEntry.getName() + " - " + phoneBookEntry.getNumber() + " - " + callsCounter;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof CallLog) {
			CallLog callLog = (CallLog) obj;
			return phoneBookEntry.equals(callLog.getPhoneBookEntry());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return phoneBookEntry.hashCode() * callsCounter;
	}

	@Override
	public int compareTo(CallLog callLog) {
		return Integer.compare(callLog.getCallsCounter(), callsCounter);
	}
}
