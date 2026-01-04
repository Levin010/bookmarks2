package com.levdev.bookmarks2.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookmarkNotFoundException extends RuntimeException {
    public BookmarkNotFoundException(Long id) {
        super(String.format("Bookmark with id=%d not found", id));
    }

    public static BookmarkNotFoundException of(Long id) {
        return new BookmarkNotFoundException(id);
    }
}
