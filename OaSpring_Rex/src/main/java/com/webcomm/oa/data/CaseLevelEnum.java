package com.webcomm.oa.data;

public enum CaseLevelEnum {
	Default_ALL(0, "全部"), ReportForControl(1, "報部列管"), DeptControl(2, "科室列管"), BureauControl(3, "局列管"),
	NoControl(4, "不列管");

	private static CaseLevelEnum[] allEnums = { Default_ALL, ReportForControl, DeptControl, BureauControl, NoControl };

	private int value;
	private String label;

	private CaseLevelEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public static CaseLevelEnum[] getAllEnums() {
		return allEnums;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static CaseLevelEnum getEnum(int value) {
		switch (value) {
		case 0:
			return Default_ALL;
		case 1:
			return ReportForControl;
		case 2:
			return DeptControl;
		case 3:
			return BureauControl;
		case 4:
			return NoControl;
		default:
			return null;
		}
	}

	public static CaseLevelEnum getEnum(String label) {
		return CaseLevelEnum.valueOf(label);
	}

//	public static void main(String[] args) {
//
//		for (int i = 0; i < allEnums.length; i++) {
//			System.out.println(allEnums[i].getValue() + "-" + allEnums[i].getLabel());
//		}
//
//	}
}
