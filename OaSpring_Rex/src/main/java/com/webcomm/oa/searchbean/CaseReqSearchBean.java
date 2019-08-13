package com.webcomm.oa.searchbean;

// TODO: Auto-generated Javadoc
/**
 * The Class CaseReqSearchBean.
 */
public class CaseReqSearchBean {

	/** The case level. */
	private String caseLevel;

	/** The case type. */
	private String caseType;

	/** The host unit. */
	private String hostUnit;

	/** The cohost unit. */
	private String cohostUnit;

	/**
	 * Instantiates a new case req search bean.
	 */
	public CaseReqSearchBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the case level.
	 *
	 * @return the caseLevel
	 */
	public String getCaseLevel() {
		return caseLevel;
	}

	/**
	 * Sets the case level.
	 *
	 * @param caseLevel the caseLevel to set
	 */
	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	/**
	 * Gets the case type.
	 *
	 * @return the caseType
	 */
	public String getCaseType() {
		return caseType;
	}

	/**
	 * Sets the case type.
	 *
	 * @param caseType the caseType to set
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	/**
	 * Gets the host unit.
	 *
	 * @return the hostUnit
	 */
	public String getHostUnit() {
		return hostUnit;
	}

	/**
	 * Sets the host unit.
	 *
	 * @param hostUnit the hostUnit to set
	 */
	public void setHostUnit(String hostUnit) {
		this.hostUnit = hostUnit;
	}

	/**
	 * Gets the cohost unit.
	 *
	 * @return the cohostUnit
	 */
	public String getCohostUnit() {
		return cohostUnit;
	}

	/**
	 * Sets the cohost unit.
	 *
	 * @param cohostUnit the cohostUnit to set
	 */
	public void setCohostUnit(String cohostUnit) {
		this.cohostUnit = cohostUnit;
	}

	@Override
	public String toString() {
		return "CaseReqSearchBean [caseLevel=" + caseLevel + ", caseType=" + caseType + ", hostUnit=" + hostUnit
				+ ", cohostUnit=" + cohostUnit + "]";
	}

}
