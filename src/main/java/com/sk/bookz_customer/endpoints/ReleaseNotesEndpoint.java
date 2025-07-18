package com.sk.bookz_customer.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Endpoint(id="releaseNotes")
@Component
public class ReleaseNotesEndpoint {

    private final List<ReleaseNotes> releaseNotesList;

    public ReleaseNotesEndpoint(List<ReleaseNotes> releaseNotesList) {
        this.releaseNotesList = releaseNotesList;
    }

    @ReadOperation
    public List<ReleaseNotes> getReleaseNotesList() {
        return releaseNotesList;
    }

    @ReadOperation
    public ReleaseNotes getReleaseNotes(@Selector String version) {

        return releaseNotesList.stream()
                .filter(releaseNote -> releaseNote.getVersion().equalsIgnoreCase(version))
                .findFirst().orElse(null);
    }

    @DeleteOperation
    public void deleteReleaseNotes(@Selector String version) {
        releaseNotesList.removeIf(releaseNote -> releaseNote.getVersion().equalsIgnoreCase(version));
    }
}
