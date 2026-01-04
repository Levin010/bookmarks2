package com.levdev.bookmarks2.domain;

public record UpdateBookmarkCommand(
        Long id,
        String title,
        String url) {
}
