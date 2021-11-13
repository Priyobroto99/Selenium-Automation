package onlineReport;

import java.util.List;

public class TestCaseModel {
	
	String tc_status=null;
	String tc_id=null;
	String tc_name=null;
	String tc_desc=null;
	String tc_executed_on=null;
	List<Steps> steps=null;
	public String getTc_status() {
		return tc_status;
	}
	public void setTc_status(String tc_status) {
		this.tc_status = tc_status;
	}
	public String getTc_id() {
		return tc_id;
	}
	public void setTc_id(String tc_id) {
		this.tc_id = tc_id;
	}
	public String getTc_name() {
		return tc_name;
	}
	public void setTc_name(String tc_name) {
		this.tc_name = tc_name;
	}
	public String getTc_desc() {
		return tc_desc;
	}
	public void setTc_desc(String tc_desc) {
		this.tc_desc = tc_desc;
	}
	public String gettc_executed_on() {
		return tc_executed_on;
	}
	public void settc_executed_on(String tc_executed_on) {
		this.tc_executed_on = tc_executed_on;
	}
	public List<Steps> getSteps() {
		return steps;
	}
	public void setSteps(List<Steps> steps) {
		this.steps = steps;
	}
	
	

}
