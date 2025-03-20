package com.booking.tests;


import com.booking.annotations.FrameworkAnnotation;
import com.booking.api.applicationApi.BookingApi;
import com.booking.api.enums.CategoryType;
import com.booking.api.enums.StatusCode;
import com.booking.constants.FrameworkConstants;
import com.booking.requests.pojo.lombok.*;
import com.booking.utils.VerificationManager;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.booking.utils.FakerUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingTest extends _BaseTest {
    private Booking testBooking;
    private int bookingId;

    @BeforeClass
    public void setup() {}

    @Story("Create a booking reservation")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "SANITY", "REGRESSION"}, description = "should be able to create booking ")
    public void shouldBeAbleToCreateABooking() {
        Booking bookingRequest = Booking.builder()
                .booking(Book.builder()
                        .firstname(generateName())
                        .lastname(generateLastName())
                        .depositpaid(true)
                        .bookingdates(BookingDates.builder().
                                checkin(generateCheckinDate()).
                                checkout(generateCheckoutDate()).
                                build())
                        .additionalneeds("Breakfast")
                        .totalprice(generatePrice())
                        .build())
                .build();
        Response response = BookingApi.createBooking(bookingRequest);

        assertStatusCode(response.statusCode(), StatusCode.CODE_200, "User should be able to create a Booking");
        assertBookingEqual(response.as(CreateBookingResponse.class), bookingRequest);
    }

    @Story("Get a booking reservation")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "SANITY", "REGRESSION"}, description = "should be able to get booking data")
    public void shouldBeAbleToGetBookingById() {
        Booking bookingRequest = Booking.builder()
                .booking(Book.builder()
                        .firstname(generateName())
                        .lastname(generateLastName())
                        .depositpaid(true)
                        .bookingdates(BookingDates.builder().
                                checkin(generateCheckinDate()).
                                checkout(generateCheckoutDate()).
                                build())
                        .additionalneeds("Breakfast")
                        .totalprice(generatePrice())
                        .build())
                .build();


        Response createResponse = BookingApi.createBooking(bookingRequest);
        assertStatusCode(createResponse.statusCode(), StatusCode.CODE_200, "User should be able to create a Booking");

        int bookingId = Integer.parseInt(createResponse.getBody().jsonPath().getString("bookingid"));
        Response getResponse = BookingApi.getBookingById(bookingId);

        assertStatusCode(getResponse.statusCode(), StatusCode.CODE_200, "User should be able to ´get a Booking by id");
        assertGetBookingEqual(getResponse.as(GetBookingResponse.class), bookingRequest);

    }

    @Story("Get all booking reservations")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "SANITY", "REGRESSION"}, description = "should be able to get all booking data")
    public void shouldBeAbleToGetAllBookings() {
        Response response = BookingApi.getAllBookings();
        assertStatusCode(response.statusCode(), StatusCode.CODE_200, "User should be able to get all booking data");
    }


    @Story("Update a booking reservation")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "SANITY", "REGRESSION"}, description = "should be able to get all booking data")
    public void shouldBeAbleToUpdateBooking() {
        if (bookingId <= 0) {
            Booking bookingRequest = Booking.builder()
                    .booking(Book.builder()
                            .firstname(generateName())
                            .lastname(generateLastName())
                            .depositpaid(true)
                            .bookingdates(BookingDates.builder().
                                    checkin(generateCheckinDate()).
                                    checkout(generateCheckoutDate()).
                                    build())
                            .additionalneeds("Breakfast")
                            .totalprice(generatePrice())
                            .build())
                    .build();

            Response response = BookingApi.createBooking(bookingRequest);
            bookingId = Integer.parseInt(response.getBody().jsonPath().getString("bookingid"));

        }
        Booking updatePayload = Booking.builder()
                .booking(Book.builder()
                        .firstname(generateName())
                        .lastname(generateLastName())
                        .depositpaid(true)
                        .bookingdates(BookingDates.builder().
                                checkin(generateCheckinDate()).
                                checkout(generateCheckoutDate()).
                                build())
                        .additionalneeds("Breakfast")
                        .totalprice(generatePrice())
                        .build())
                .build();

        Response updateResponse = BookingApi.updateBooking(bookingId, updatePayload);

    }


    @Story("Update a booking reservation partially")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "SANITY", "REGRESSION"}, description = "should be able to update booking with patch method")
    public void shouldBeAbleToUpdateBookingWithPatchMethod() {
        if (bookingId <= 0) {
            Booking bookingRequest = Booking.builder()
                    .booking(Book.builder()
                            .firstname(generateName())
                            .lastname(generateLastName())
                            .depositpaid(true)
                            .bookingdates(BookingDates.builder().
                                    checkin(generateCheckinDate()).
                                    checkout(generateCheckoutDate()).
                                    build())
                            .additionalneeds("Breakfast")
                            .totalprice(generatePrice())
                            .build())
                    .build();
            ;
            Response createResponse = BookingApi.createBooking(bookingRequest);
            bookingId = Integer.parseInt(createResponse.getBody().jsonPath().getString("bookingid"));
        }

        String partialUpdate = "{\"totalprice\": 250, \"additionalneeds\": \"Lunch\"}";
        Response patchResponse = BookingApi.partialUpdateBooking(bookingId, partialUpdate);

        assertStatusCode(patchResponse.statusCode(), StatusCode.CODE_200, "User should be able to update booking with patch method");

        // Verify the updated fields
        VerificationManager.validateResponse(patchResponse.jsonPath().getInt("totalprice"), 250,
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> TOTALPRICE </u> </b>");
        VerificationManager.validateResponse(patchResponse.jsonPath().getString("additionalneeds"), "Lunch",
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> ADDITIONAL NEEDS </u> </b>");
    }

    @Story("Delete a booking reservation")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "SANITY", "REGRESSION"}, description = "should be able to delete booking")
    public void shouldBeAbleToDeleteBooking() {
        if (bookingId <= 0) {
            Booking bookingRequest = Booking.builder()
                    .booking(Book.builder()
                            .firstname(generateName())
                            .lastname(generateLastName())
                            .depositpaid(true)
                            .bookingdates(BookingDates.builder().
                                    checkin(generateCheckinDate()).
                                    checkout(generateCheckoutDate()).
                                    build())
                            .additionalneeds("Breakfast")
                            .totalprice(generatePrice())
                            .build())
                    .build();
            Response createResponse = BookingApi.createBooking(bookingRequest);
            bookingId = Integer.parseInt(createResponse.getBody().jsonPath().getString("bookingid"));
        }

        // Delete the booking
        Response deleteResponse = BookingApi.deleteBooking(bookingId);
        assertStatusCode(deleteResponse.statusCode(), StatusCode.CODE_200, "User should be able to delete booking");

        // Verify booking is deleted by trying to get it
        Response getResponse = BookingApi.getBookingById(bookingId);
        assertStatusCode(getResponse.statusCode(), StatusCode.CODE_404, "Deleted booking should not be found");
    }

    @Story("Filter booking reservation by name/lastname/checkin-checkout-date")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @FrameworkAnnotation(category = {CategoryType.SMOKE, CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "SANITY", "REGRESSION"}, description = "should be able to filter bookings by firstname")
    public void shouldBeAbleToFilterBookingsByFirstname() {
        Booking bookingRequest = Booking.builder()
                .booking(Book.builder()
                        .firstname("berkay")
                        .lastname("pabuccu")
                        .depositpaid(true)
                        .bookingdates(BookingDates.builder()
                                .checkin(generateCheckinDate())
                                .checkout(generateCheckoutDate())
                                .build())
                        .additionalneeds("Breakfast")
                        .totalprice(generatePrice())
                        .build())
                .build();

        Response createResponse = BookingApi.createBooking(bookingRequest);
        assertStatusCode(createResponse.statusCode(), StatusCode.CODE_200, "User should be able to create a Booking");

        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("firstname", "berkay");
        formParams.put("lastname", "pabuccu");
        //formParams.put("checkin", "11.11.1111");
        //formParams.put("checkout", "11.11.1111");

        Response filterResponse = BookingApi.getBookingByFilter(formParams);
        assertStatusCode(filterResponse.statusCode(), StatusCode.CODE_200, "User should be able to filter bookings by firstname");

        GetAllBookingsResponse[] bookings = filterResponse.as(GetAllBookingsResponse[].class);
        assertThat(bookings, not(emptyArray()));

    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode, String message) {
        assertThat(actualStatusCode, equalTo(statusCode.code));
        VerificationManager.validateResponse(actualStatusCode, statusCode.code,
                FrameworkConstants.ASSERTION_FOR_RESPONSE_STATUS_CODE + " - <b> <u> " + message + " </u> </b>");

    }

    @Step
    public void assertBookingEqual(CreateBookingResponse responseBooking, Booking requestBooking) {
        assertThat(responseBooking.getBookingid(), notNullValue());
        assertThat(responseBooking.getBooking(), notNullValue());

        VerificationManager.validateResponse(responseBooking.getBooking().getFirstname(), requestBooking.getBooking().getFirstname(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> FIRSTNAME </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBooking().getLastname(), requestBooking.getBooking().getLastname(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> LASTNAME </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBooking().getTotalprice(), requestBooking.getBooking().getTotalprice(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> TOTALPRICE </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBooking().getDepositpaid(), requestBooking.getBooking().getDepositpaid(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> DEPOSITPAID </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBooking().getBookingdates().getCheckin(), requestBooking.getBooking().getBookingdates().getCheckin(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> CHECKIN DATE </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBooking().getBookingdates().getCheckout(), requestBooking.getBooking().getBookingdates().getCheckout(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> CHECKOUT DATE </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBooking().getAdditionalneeds(), requestBooking.getBooking().getAdditionalneeds(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> ADDITIONAL NEEDS </u> </b>");
    }

    @Step
    public void assertGetBookingEqual(GetBookingResponse responseBooking, Booking requestBooking) {
        VerificationManager.validateResponse(responseBooking.getBook().getFirstname(), requestBooking.getBooking().getFirstname(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> FIRSTNAME </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBook().getLastname(), requestBooking.getBooking().getLastname(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> LASTNAME </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBook().getTotalprice(), requestBooking.getBooking().getTotalprice(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> TOTALPRICE </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBook().getDepositpaid(), requestBooking.getBooking().getDepositpaid(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> DEPOSITPAID </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBook().getBookingdates().getCheckin(), requestBooking.getBooking().getBookingdates().getCheckin(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> CHECKIN DATE </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBook().getBookingdates().getCheckout(), requestBooking.getBooking().getBookingdates().getCheckout(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> CHECKOUT DATE </u> </b>");
        VerificationManager.validateResponse(responseBooking.getBook().getAdditionalneeds(), requestBooking.getBooking().getAdditionalneeds(),
                FrameworkConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD + " - <b> <u> ADDITIONAL NEEDS </u> </b>");
    }


}
