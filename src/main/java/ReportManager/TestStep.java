package ReportManager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestStep {
	
	@SerializedName("StepID")
	@Expose
	private int stepID;
	@SerializedName("StepName")
	@Expose
	private String stepName;
	@SerializedName("StepDesc")
	@Expose
	private String stepDesc;
	@SerializedName("Status")
	@Expose
	private Boolean status;
	@SerializedName("Screenshot")
	@Expose
	private String screenshot;

	public int getStepID() {
		return stepID;
	}

	public void setStepID(int stepID) {
		this.stepID = stepID;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}
}
