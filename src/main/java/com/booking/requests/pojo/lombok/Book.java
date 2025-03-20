package com.booking.requests.pojo.lombok;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@JsonPropertyOrder({
        "firstname",
        "lastname",
        "totalprice",
        "depositpaid",
        "bookingdates",
        "additionalneeds"
})
@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @JsonProperty("firstname")
    public String firstname;

    @JsonProperty("lastname")
    public String lastname;

    @JsonProperty("totalprice")
    public Integer totalprice;

    @JsonProperty("depositpaid")
    public Boolean depositpaid;

    @JsonProperty("bookingdates")
    public BookingDates bookingdates;

    @JsonProperty("additionalneeds")
    public String additionalneeds;
}
