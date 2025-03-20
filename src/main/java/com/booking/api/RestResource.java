package com.booking.api;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.booking.constants.FrameworkConstants;
import com.booking.reports.ExtentLogger;
import com.booking.utils.ConfigLoader;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;

import static com.booking.api.Route.AUTH;
import static com.booking.api.SpecBuilder.getRequestSpec;
import static com.booking.api.SpecBuilder.getResponseSpec;
import static com.booking.api.applicationApi.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response authorizeAccount(Object payload) {

        StringWriter writerRequest;
        PrintStream captor;
        writerRequest = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writerRequest), true);

        Response response =
                given(getRequestSpec()).
                        body(payload).
                        //formParams(formParams).
                        //auth().basic("admin", "admin").
                                filter(new RequestLoggingFilter(captor)).
                        when().
                        post(AUTH).
                        then().
                        spec(getResponseSpec()).
                        extract().response();
        printDetailsInExtentReport(writerRequest, response);
        return response;

    }

    public static Response post(String path, Object payLoad) {

        StringWriter writerRequest;
        PrintStream captor;
        writerRequest = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writerRequest), true);

        Response response =
                given(getRequestSpec()).
                        body(payLoad).
                        //auth().basic("admin", "admin").
                                filter(new RequestLoggingFilter(captor)).
                        when().
                        post(path).
                        then().
                        spec(getResponseSpec()).
                        extract().response();

        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    public static Response get(String path) {

        StringWriter writerRequest;
        PrintStream captor;
        writerRequest = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writerRequest), true);

        Response response =
                given(getRequestSpec()).
                        filter(new RequestLoggingFilter(captor)).
                        when().
                        get(path).
                        then().
                        spec(getResponseSpec()).
                        extract().
                        response();

        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    public static Response getBookingByFilter(String path, HashMap<String, String> formParams) {

        StringWriter writerRequest;
        PrintStream captor;
        writerRequest = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writerRequest), true);

        Response response =

                given(getRequestSpec()).
                        formParams(formParams).
                        filter(new RequestLoggingFilter(captor)).
                        when().

                        get(path).
                        then().
                        spec(getResponseSpec()).
                        extract().response();
        printDetailsInExtentReport(writerRequest, response);
        return response;

    }

    public static Response put(String path, Object payLoad) {
        StringWriter writerRequest;
        PrintStream captor;
        writerRequest = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writerRequest), true);

        Response response =
                given(getRequestSpec()).
                        body(payLoad).
                        cookie("token", getToken()).
                        //auth().basic(uname,password).
                                filter(new RequestLoggingFilter(captor)).
                        when().
                        put(path).
                        then().
                        extract().response();

        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    public static Response patch(String path, Object payLoad) {
        StringWriter writerRequest;
        PrintStream captor;
        writerRequest = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writerRequest), true);

        Response response =
                given(getRequestSpec()).
                        body(payLoad).
                        //auth().basic(uname,password).
                                cookie("token", getToken()).
                        filter(new RequestLoggingFilter(captor)).
                        when().
                        patch(path).
                        then().
                        extract().response();

        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    public static Response delete(String path) {
        StringWriter writerRequest;
        PrintStream captor;
        writerRequest = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writerRequest), true);

        Response response =
                given(getRequestSpec()).
                        cookie("token", getToken()).
                        filter(new RequestLoggingFilter(captor)).
                        when().
                        delete(path).
                        then().
                        spec(getResponseSpec()).
                        extract().
                        response();

        printDetailsInExtentReport(writerRequest, response);
        return response;
    }

    private static void printDetailsInExtentReport(StringWriter writer, Response response) {
        if (ConfigLoader.getInstance().getRequestDetailsInReports().equalsIgnoreCase(FrameworkConstants.getYes())) {
            ExtentLogger.info("<details><summary><i><font color=black> Request details: </font></i>" + "</summary>"
                    + "<pre>" + writer.toString() + "</pre>" + "</details> \n");
            ExtentLogger.info("<details><summary><i><font color=black> Response details: </font></i>" + "</summary>"
                    + "<pre>" + response.asString() + "</pre>" + "</details> \n");
            ExtentLogger.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        }
    }


}

