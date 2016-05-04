package com.naskoni.phonebook.io;

import com.naskoni.phonebook.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {

	@Override
	public void printline(Object object) {
		System.out.println(object);
	}
}
