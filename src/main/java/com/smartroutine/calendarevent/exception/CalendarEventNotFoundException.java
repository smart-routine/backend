package com.smartroutine.calendarevent.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;

public class CalendarEventNotFoundException extends EntityNotFoundException {
  public CalendarEventNotFoundException(UUID eventId) {
    super(eventId.toString() + " not found");
  }
}
