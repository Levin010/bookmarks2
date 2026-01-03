package com.levdev.bookmarks2.domain;

import java.time.Instant;

public record BookmarkDTO(
        Long id,
        String title,
        String url,
        Instant createdAt) {}
