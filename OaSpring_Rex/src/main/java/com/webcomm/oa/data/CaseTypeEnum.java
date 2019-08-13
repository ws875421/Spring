package com.webcomm.oa.data;

public enum CaseTypeEnum {

	Default_ALL(0, "全部"), General(1, "總綱目"), TempControl(2, "臨時列管"), NoControl(3, "非列管(原A類案件)");

	private static CaseTypeEnum[] allEnums = { Default_ALL, General, TempControl, NoControl };

	private int value;
	private String label;

	private CaseTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public static CaseTypeEnum[] getAllEnums() {
		return allEnums;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static CaseTypeEnum getEnum(int value) {
		switch (value) {
		case 0:
			return Default_ALL;
		case 1:
			return General;
		case 2:
			return TempControl;
		case 3:
			return NoControl;
		default:
			return null;
		}
	}

	public static CaseTypeEnum getEnum(String label) {
		return CaseTypeEnum.valueOf(label);
	}
	


}
