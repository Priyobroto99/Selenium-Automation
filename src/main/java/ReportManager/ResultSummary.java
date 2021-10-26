package ReportManager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultSummary {

	@SerializedName("TC_ID")
	@Expose
	private String tCID;
	@SerializedName("TC_Desc")
	@Expose
	private String tCDesc;
	@SerializedName("Status")
	@Expose
	private Boolean status;
	@SerializedName("Path")
	@Expose
	private String path;
	
	public String getTCID() {
		return tCID;
	}

	public void setTCID(String tCID) {
		this.tCID = tCID;
	}

	public String getTCDesc() {
		return tCDesc;
	}

	public void setTCDesc(String tCDesc) {
		this.tCDesc = tCDesc;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
