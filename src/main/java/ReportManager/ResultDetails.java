package ReportManager;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDetails {
	@SerializedName("TC_ID")
	@Expose
	private String tCID;
	@SerializedName("TC_Desc")
	@Expose
	private String tCDesc;
	@SerializedName("TestSteps")
	@Expose
	private List<TestStep> testSteps = null;

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

	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

}
