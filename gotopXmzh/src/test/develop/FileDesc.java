package test.develop;

public class FileDesc {

	private String path = null;
	private long tm;

	public FileDesc(String path, long tm) {
		super();
		this.path = path;
		this.tm = tm;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getTm() {
		return tm;
	}

	public void setTm(long tm) {
		this.tm = tm;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof FileDesc && this.path.equals(((FileDesc) o).path) && this.tm == ((FileDesc) o).tm;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 37 + (int) (tm ^ (tm >>> 32));
		result = result * 37 + this.path.hashCode();
		return result;
	}

}