package edu.cnm.deepdive.codebreaker.model;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public record Guess(String text, int exactMatches, int nearMatches, boolean solution,
                    OffsetDateTime submitted) {

}
