package com.booking.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FakerUtils {

    public static String generateAnimalName() {
        Faker faker = new Faker();
        return faker.animal().name();
    }

    public static String generateStatus() {
        List<String> statuses = Arrays.asList("available", "pending", "sold");
        Random random = new Random();
        return statuses.get(random.nextInt(statuses.size()));
    }

    public static String generateDescription() {
        Faker faker = new Faker();
        return "Description " + faker.regexify("[ A-Za-z0-9_@./#&+-]{50}");
    }

    public static Integer generateNumber() {
        Faker faker = new Faker();
        faker.random();
        return faker.number().numberBetween(0, 5000);
    }


    public static String generateCategoryName() {
        Faker faker = new Faker();
        return "Category " + faker.regexify("[A-Za-z0-9 ,_-]{10}");
    }

    public static String generateName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String generateLastName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static Integer generatePrice() {
        Faker faker = new Faker();
        return faker.random().nextInt(100, 5000);
    }


    public static String generateCheckinDate() {
        Faker faker = new Faker();
        LocalDate futureDate = LocalDate.now().plusDays(faker.number().numberBetween(1, 30));
        return futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String generateCheckoutDate() {
        Faker faker = new Faker();
        LocalDate futureDate = LocalDate.now().plusDays(faker.number().numberBetween(31, 60));
        return futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
