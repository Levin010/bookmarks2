package com.levdev.bookmarks2.domain;

import java.time.Instant;

public record BookmarkDTO(
        Long id,
        String title,
        String url,
        Instant createdAt,
        Instant updatedAt
) {
    static BookmarkDTO from(Bookmark bookmark) {
        return new BookmarkDTO(
                bookmark.getId(),
                bookmark.getTitle(),
                bookmark.getUrl(),
                bookmark.getCreatedAt(),
                bookmark.getUpdatedAt()
        );
    }
}
