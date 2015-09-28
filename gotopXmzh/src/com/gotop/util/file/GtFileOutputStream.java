package com.gotop.util.file;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GtFileOutputStream extends FileOutputStream {

	public GtFileOutputStream(String name, boolean append)
			throws FileNotFoundException {
		super(name, append);
	}

	public GtFileOutputStream(String name) throws FileNotFoundException {
		super(name);
	}

	public GtFileOutputStream(FileDescriptor fdObj) {
		super(fdObj);
	}

	public GtFileOutputStream(File file, boolean append)
			throws FileNotFoundException {
		super(file, append);
	}

	public GtFileOutputStream(File file) throws FileNotFoundException {
		super(file);
	}

}
