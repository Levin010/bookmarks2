package com.levdev.bookmarks2.api;

import com.levdev.bookmarks2.api.models.ApiError;
import com.levdev.bookmarks2.domain.BookmarkNotFoundException;
import com.levdev.bookmarks2.domain.BookmarkTitleNotAllowedException;
import com.levdev.bookmarks2.domain.DuplicateBookmarkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateBookmarkException.class)
    public ResponseEntity<ApiError> handleDuplicateBookmarkException(DuplicateBookmarkException e) {
        ApiError error = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BookmarkTitleNotAllowedException.class)
    public ResponseEntity<ApiError> handleBookmarkTitleNotAllowedException(BookmarkTitleNotAllowedException e) {
        ApiError error = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(BookmarkNotFoundException.class)
    public ResponseEntity<ApiError> handleBookmarkNotFoundException(BookmarkNotFoundException e) {
        ApiError error = new ApiError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}