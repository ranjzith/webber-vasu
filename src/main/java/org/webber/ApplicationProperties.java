package org.webber;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class ApplicationProperties {

	private String processDirPath;
	private String driver;
	private String shOne;
	private String shTwo;

	private List<String> form1List1;
	private List<String> form1List2;
	private List<String> form2List1;
	private List<String> form2List2;

	public String getProcessDirPath() {
		return processDirPath;
	}

	public void setProcessDirPath(String processDirPath) {
		this.processDirPath = processDirPath;
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getShOne() {
		return shOne;
	}

	public void setShOne(String shOne) {
		this.shOne = shOne;
	}

	public String getShTwo() {
		return shTwo;
	}

	public void setShTwo(String shTwo) {
		this.shTwo = shTwo;
	}

	public List<String> getForm1List1() {
		return form1List1;
	}

	public void setForm1List1(List<String> form1List1) {
		this.form1List1 = form1List1;
	}

	public List<String> getForm1List2() {
		return form1List2;
	}

	public void setForm1List2(List<String> form1List2) {
		this.form1List2 = form1List2;
	}

	public List<String> getForm2List1() {
		return form2List1;
	}

	public void setForm2List1(List<String> form2List1) {
		this.form2List1 = form2List1;
	}

	public List<String> getForm2List2() {
		return form2List2;
	}

	public void setForm2List2(List<String> form2List2) {
		this.form2List2 = form2List2;
	}
}
