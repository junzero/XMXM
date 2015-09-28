package com.gotop.util.file;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GtFileInputStream extends FileInputStream{

	public GtFileInputStream(String name) throws FileNotFoundException {
		super(name);
	}

	public GtFileInputStream(FileDescriptor fdObj) {
		super(fdObj);
	}

	public GtFileInputStream(File file) throws FileNotFoundException {
		super(file);
	}

}
