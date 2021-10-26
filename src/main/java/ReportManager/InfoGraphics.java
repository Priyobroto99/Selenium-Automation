package ReportManager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoGraphics {
	
	@SerializedName("totalTestCasesCount")
	@Expose
	private String totalTestCasesCount;
	
	@SerializedName("totalFaliedTestCaseCount")
	@Expose
	private String totalFaliedTestCaseCount;
	
	@SerializedName("totalPassedTestCaseCount")
	@Expose
	private String totalPassedTestCaseCount;
	
	@SerializedName("executionStartTime")
	@Expose
	private String executionStartTime;
	
	@SerializedName("executionFinsihTime")
	@Expose
	private String executionFinsihTime;
	
	public String getExecutionStartTime() {
		return executionStartTime;
	}

	public void setExecutionStartTime(String executionStartTime) {
		this.executionStartTime = executionStartTime;
	}

	public String getExecutionFinsihTime() {
		return executionFinsihTime;
	}

	public void setExecutionFinsihTime(String executionFinsihTime) {
		this.executionFinsihTime = executionFinsihTime;
	}

	public String getTotalPassedTestCaseCount() {
		return totalPassedTestCaseCount;
	}

	public void setTotalPassedTestCaseCount(String totalPassedTestCaseCount) {
		this.totalPassedTestCaseCount = totalPassedTestCaseCount;
	}

	public String getTotalTestCasesCount() {
		return totalTestCasesCount;
	}

	public void setTotalTestCasesCount(String totalTestCasesCount) {
		this.totalTestCasesCount = totalTestCasesCount;
	}

	public String getTotalFaliedTestCaseCount() {
		return totalFaliedTestCaseCount;
	}

	public void setTotalFaliedTestCaseCount(String totalFaliedTestCaseCount) {
		this.totalFaliedTestCaseCount = totalFaliedTestCaseCount;
	}
}
