
package com.booking.utils;

import java.util.Properties;

public class DataLoader {

    private static final String GET_BOOKING_ID = "get_booking_id";

    private static final String UPDATE_BOOKING_ID = "update_booking_id";
    private static final String DELETE_BOOKING_ID = "delete_booking_id";

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    private Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader(RESOURCES_PATH + "data.properties");
    }

    public static DataLoader getInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }


    public String get_GetBookingID() {
        String prop = properties.getProperty(GET_BOOKING_ID);
        if (prop != null) {
            return prop.trim();
        } else {
            throw new RuntimeException(
                    "Property " + GET_BOOKING_ID + " is not specified in the data.properties file");
        }
    }


    public String get_UpdateBookingID() {
        String prop = properties.getProperty(UPDATE_BOOKING_ID);
        if (prop != null) {
            return prop.trim();
        } else {
            throw new RuntimeException(
                    "Property " + UPDATE_BOOKING_ID + " is not specified in the data.properties file");
        }
    }

    public String get_DeleteBookingID() {
        String prop = properties.getProperty(DELETE_BOOKING_ID);
        if (prop != null) {
            return prop.trim();
        } else {
            throw new RuntimeException(
                    "Property " + DELETE_BOOKING_ID + " is not specified in the data.properties file");
        }
    }
}