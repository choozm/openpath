package openpath;

import java.nio.file.Path;

public class Result {

	private boolean success = false;
	private String user;
	private String msg;
	private Path downloadFile;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Path getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(Path downloadFile) {
		this.downloadFile = downloadFile;
	}

	@Override
	public String toString() {
		return (String.format("%1$s - %2$s", user, (success? "OK": "FAIL")));

	}

}
