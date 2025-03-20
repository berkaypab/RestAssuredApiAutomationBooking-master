package com.booking.constants;

import java.util.Date;

import com.booking.utils.ConfigLoader;
import com.booking.utils.OSInfoUtils;

public class FrameworkConstants {

    private static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String ASSERTION_FOR_RESPONSE_STATUS_CODE = "Assertion for Response Status Code";
    public static final String ASSERTION_FOR_RESPONSE_HEADER = "Assertion for Response Headers";
    public static final String ASSERTION_FOR_RESPONSE_CUSTOM_FIELD = "Assertion for Response Custom Field";

    private static final String YES = "yes";
    private static final String NO = "no";

    private static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + "/ExtentReports/";
    private static final String EXTENT_REPORT_NAME = "AutomationReport.html";
    private static String extentReportFilePath = "";

    public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";
    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";

    private static final String Project_Name = "Automation Test Suite Report";

    public static String getProjectPath() {
        return PROJECT_PATH;
    }

    public static String getProjectName() {
        return Project_Name;
    }

    public static String getYes() {
        return YES;
    }

    public static String getNo() {
        return NO;
    }

    public static String getExtentReportName() {
        return EXTENT_REPORT_NAME;
    }

    public static String getExtentReportFolderPath() {
        return EXTENT_REPORT_FOLDER_PATH;
    }

    public static String getExtentReportFilePath() {

        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    private static String createReportPath() {
        if (ConfigLoader.getInstance().getOverrideReports().equalsIgnoreCase(getNo())) {
            /*
             * Report name -> Windows_10_Tue_Oct_05_02_30_46_IST_2021_AutomationReport.html
             */
            return EXTENT_REPORT_FOLDER_PATH + OSInfoUtils.getOSInfo() + "_" + getCurrentDate() + "_"
                    + getExtentReportName();

        } else {
            return EXTENT_REPORT_FOLDER_PATH + getExtentReportName();
        }
    }

    private static String getCurrentDate() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }

    public static void setExtentReportFilePath(String extentReportFilePath) {
        FrameworkConstants.extentReportFilePath = extentReportFilePath;
    }
}
