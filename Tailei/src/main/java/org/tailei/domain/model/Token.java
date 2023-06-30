package org.tailei.domain.model;


import java.util.List;
import java.util.stream.Collectors;

public class Token extends BaseModel{

	String applicationCsi;
	String applicationName;

	public String getApplicationCsi() {
		return applicationCsi;
	}

	public void setApplicationCsi(String applicationCsi) {
		this.applicationCsi = applicationCsi;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

}
