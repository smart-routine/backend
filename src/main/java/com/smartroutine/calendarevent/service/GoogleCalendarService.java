package com.smartroutine.calendarevent.service;

import com.smartroutine.calendarevent.dto.google.GoogleCalendarApiRequest;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarApiResponse;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarCreateRequest;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarCreateResponse;
import com.smartroutine.calendarevent.dto.google.GoogleCalendarDateTime;
import com.smartroutine.calendarevent.exception.GoogleAccessToKenMissing;
import com.smartroutine.calendarevent.exception.GoogleCalenderCreateFiled;
import com.smartroutine.user.entity.User;
import com.smartroutine.user.repository.UserRepository;
import java.time.ZoneId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class GoogleCalendarService {

    private static final String GOOGLE_CALENDAR_EVENT_URL = "https://www.googleapis.com/calendar/v3/calendars/primary/events";

    private final UserRepository userRepository;
    private final RestClient restClient;

    public GoogleCalendarCreateResponse createGoogleCalendar(
        UUID userId,
        GoogleCalendarCreateRequest request) {

        User user = userRepository.findById(userId).orElseThrow();

        String accessToken = user.getGoogleAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            throw new GoogleAccessToKenMissing();
        }

        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        GoogleCalendarDateTime start = new GoogleCalendarDateTime(
            request.getStartAt().atZone(zoneId).toOffsetDateTime(),"Asia/Seoul"
        );
        GoogleCalendarDateTime end = new GoogleCalendarDateTime(
            request.getEndAt().atZone(zoneId).toOffsetDateTime(),"Asia/Seoul"
        );

        GoogleCalendarApiRequest googleCalendarApiRequest = new GoogleCalendarApiRequest(
            request.getTitle(),
            request.getDescription(),
            start,
            end
        );

        GoogleCalendarApiResponse googleApiResponse = restClient.post()
            .uri(GOOGLE_CALENDAR_EVENT_URL)
            .headers(headers -> headers.setBearerAuth(accessToken))
            .body(googleCalendarApiRequest)
            .retrieve()
            .body(GoogleCalendarApiResponse.class);

        if(googleApiResponse == null || googleApiResponse.id() == null) {
            throw new GoogleCalenderCreateFiled();
        }

        return new GoogleCalendarCreateResponse(googleApiResponse.id(), googleApiResponse.status());
    }
}
