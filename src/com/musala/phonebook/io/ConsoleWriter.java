package com.musala.phonebook.io;

import com.musala.phonebook.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {

	@Override
	public void printline(Object object) {
		System.out.println(object);
	}
}
