package com.booking.api.applicationApi;


import com.booking.api.RestResource;
import com.booking.requests.pojo.lombok.Booking;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.booking.api.Route.BOOKING;

public class BookingApi {


    public static Response createBooking(Booking booking) {
        return RestResource.post(BOOKING, booking);
    }

    public static Response getAllBookings() {
        return RestResource.get(BOOKING);
    }

    public static Response getBookingById(int id) {
        return RestResource.get(BOOKING + "/" + id);
    }

    public static Response updateBooking(int id, Booking booking) {
        return RestResource.put(BOOKING + "/" + id, booking);
    }

    public static Response partialUpdateBooking(int id, Object booking) {
        return RestResource.patch(BOOKING + "/" + id, booking);
    }

    public static Response deleteBooking(int id) {
        return RestResource.delete(BOOKING + "/" + id);
    }

    public static Response getBookingByFilter(HashMap<String, String> formParams) {


        return RestResource.getBookingByFilter(BOOKING, formParams);
    }


}