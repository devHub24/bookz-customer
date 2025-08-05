package com.sk.bookz_customer.configuration;

import com.sk.bookz_customer.endpoints.ReleaseItem;
import com.sk.bookz_customer.endpoints.ReleaseNotes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class CustomerConfiguration {


    @Bean(name="releaseNotes")
    public List<ReleaseNotes> releaseNotes(){
        return List.of(ReleaseNotes.builder()
                        .version("1.0.1")
                        .commitId("#23456")
                        .bugFixes(Set.of(ReleaseItem.builder()
                                        .itemId("S123")
                                        .itemDescription("Some description-1")
                                .build(),
                                ReleaseItem.builder()
                                        .itemId("S124")
                                        .itemDescription("Some description-2")
                                        .build()))
                .build(),
                ReleaseNotes.builder()
                        .version("1.0.2")
                        .commitId("#34567")
                        .newReleases(Set.of(
                                ReleaseItem.builder()
                                        .itemId("S125")
                                        .itemDescription("Some description-3")
                                        .build()
                        ))
                        .build());
    }
}
