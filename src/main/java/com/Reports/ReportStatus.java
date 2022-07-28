package com.Reports;

public enum ReportStatus {
	BUSINESSSTEP,//Used to create a Parent Node. Use this first before using pass,fail, etc.
	pass,//Adds log messages without screenshot
	fail,//Adds log messages without screenshot
	Pass,//Adds log messages with screenshot
	Fail,//Adds log messages with screenshot
	PASS,//Adds log messages with full page screenshot
	FAIL,//Adds log messages with full page screenshot
	VIDEO//Records the video, but use it after ScreenRecorderUtil.stopRecord() method 
}
