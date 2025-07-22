package com.sk.bookz_customer.endpoints;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseNotes {

    private String version;
    private String commitId;
    private Set<ReleaseItem> bugFixes;
    private Set<ReleaseItem> newReleases;
}
