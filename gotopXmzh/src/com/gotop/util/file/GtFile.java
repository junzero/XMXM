package com.gotop.util.file;

import java.io.File;
import java.net.URI;

public class GtFile extends File{
	private static final long serialVersionUID = 1289228111838837513L;
	
	public GtFile(String pathname){
		super(pathname);
	}
	public GtFile(File parent, String child) {
		super(parent, child);
	}
	public GtFile(URI uri){
		super(uri);
	}
	public GtFile(String parent, String child){
		super(parent,child);
	}
}
