package com.college.service;

import com.college.domain.Guest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GuestUIService {

    private static final String BASE_URL = "http://localhost:8080/guest";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // ----------------- CREATE -----------------
    public Guest addGuest(Guest guest) throws Exception {
        String endpoint = BASE_URL + "/create";
        String json = objectMapper.writeValueAsString(guest);

        HttpURLConnection conn = setupConnection(endpoint, "POST");
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        checkResponse(conn);
        return objectMapper.readValue(conn.getInputStream(), Guest.class);
    }

    // ----------------- READ -----------------
    public Guest getGuest(int guestId) throws Exception {
        String endpoint = BASE_URL + "/read/" + guestId;

        HttpURLConnection conn = setupConnection(endpoint, "GET");
        checkResponse(conn);

        return objectMapper.readValue(conn.getInputStream(), Guest.class);
    }

    // ----------------- UPDATE -----------------
    public Guest updateGuest(Guest guest) throws Exception {
        String endpoint = BASE_URL + "/update";
        String json = objectMapper.writeValueAsString(guest);

        HttpURLConnection conn = setupConnection(endpoint, "PUT");
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        checkResponse(conn);
        return objectMapper.readValue(conn.getInputStream(), Guest.class);
    }

    // ----------------- DELETE -----------------
    public boolean deleteGuest(int guestId) throws Exception {
        String endpoint = BASE_URL + "/delete/" + guestId;

        HttpURLConnection conn = setupConnection(endpoint, "DELETE");
        checkResponse(conn);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return Boolean.parseBoolean(br.readLine());
        }
    }

    // ----------------- GET ALL -----------------
    public List<Guest> getAllGuests() throws Exception {
        String endpoint = BASE_URL + "/getall";

        HttpURLConnection conn = setupConnection(endpoint, "GET");
        checkResponse(conn);

        return objectMapper.readValue(conn.getInputStream(), new TypeReference<List<Guest>>() {});
    }

    // ----------------- HELPERS -----------------
    private HttpURLConnection setupConnection(String endpoint, String method) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        return conn;
    }

    private void checkResponse(HttpURLConnection conn) throws Exception {
        int status = conn.getResponseCode();
        if (status >= 400) {
            throw new RuntimeException("HTTP request failed with code: " + status);
        }
    }
}
