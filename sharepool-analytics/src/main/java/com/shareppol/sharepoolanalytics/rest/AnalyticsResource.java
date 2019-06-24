package com.shareppol.sharepoolanalytics.rest;

import com.shareppol.sharepoolanalytics.domain.AnalyticsEntry;
import com.shareppol.sharepoolanalytics.logic.AnalyticsRestRequestHandler;
import io.swagger.annotations.Api;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Api(tags = "Analytics", description = "Get analytics data")
@RestController
@RequestMapping("analytics")
public class AnalyticsResource {

    private final AnalyticsRestRequestHandler requestHandler;

    public AnalyticsResource(AnalyticsRestRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @GetMapping(path = "/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<LocalDate, AnalyticsEntry> getAnalyticsForTimeSpan(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String userToken) {
        return requestHandler.getAnalyticsForTimeSpan(userToken, from, to);
    }
}
