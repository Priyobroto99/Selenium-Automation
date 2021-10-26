package ReportManager;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report {

	@SerializedName("ResultSummary")
	@Expose
	private List<ResultSummary> resultSummary = null;
	@SerializedName("ResultDetails")
	@Expose
	private List<ResultDetails> resultDetails = null;
	@SerializedName("InfoGraphics")
	@Expose
	private List<InfoGraphics> infoGraphics = null;
	
	
	public List<InfoGraphics> getInfoGraphics() {
		return infoGraphics;
	}
	public void setInfoGraphics(List<InfoGraphics> infoGraphics) {
		this.infoGraphics = infoGraphics;
	}
	/**
	 *
	 * @return The ResultSummary
	 */
	public List<ResultSummary> getResultSummary() {
		return resultSummary;
	}
	/**
	 *
	 * @return The ResultDetails
	 */
	public List<ResultDetails> getResultDetails() {
		return resultDetails;
	}
	/**
	 *
	 * @param resultSummary
	 * The resultSummary
	 */
	public void setResultSummary(List<ResultSummary> resultSummary) {
		this.resultSummary = resultSummary;
	}	
	/**
	 *
	 * @param resultDetails
	 * The resultDetails
	 */
	public void setResultDetails(List<ResultDetails> resultDetails) {
		this.resultDetails = resultDetails;
	}

}
