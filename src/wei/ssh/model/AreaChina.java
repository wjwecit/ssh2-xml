package wei.ssh.model;

import java.io.Serializable;

/**
 * @author Jerry
 *
 */

public class AreaChina implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7859704013566331716L;
	private int areaCode;	
	private String areaName;	
	private String areaCodeDeprecated;	
	
	public int getAreaCode() {
		return areaCode;
	}

	
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getAreaCodeDeprecated() {
		return areaCodeDeprecated;
	}

	public void setAreaCodeDeprecated(String areaCodeDeprecated) {
		this.areaCodeDeprecated = areaCodeDeprecated;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
