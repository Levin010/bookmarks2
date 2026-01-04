package com.levdev.bookmarks2.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BookmarkTitleNotAllowedException extends RuntimeException {
    public BookmarkTitleNotAllowedException(String message) {
        super(message);
    }
}
