package com.levdev.bookmarks2.api.controllers;

import com.levdev.bookmarks2.api.models.CreateBookmarkRequest;
import com.levdev.bookmarks2.api.models.UpdateBookmarkRequest;
import com.levdev.bookmarks2.domain.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/bookmarks")
class BookmarkController {
    private final BookmarkService bookmarkService;

    BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    PagedResult<BookmarkDTO> findBookmarks(
            @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize) {
        FindBookmarksQuery query = new FindBookmarksQuery(pageNo, pageSize);
        return bookmarkService.findBookmarks(query);
    }

    @PostMapping
    ResponseEntity<BookmarkDTO> create(@RequestBody @Valid CreateBookmarkRequest request) {
        CreateBookmarkCommand cmd = new CreateBookmarkCommand(request.title(), request.url());
        BookmarkDTO bookmark = bookmarkService.create(cmd);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookmark.id()).toUri();
        return ResponseEntity.created(location).body(bookmark);
    }

    @PutMapping("/{id}")
    void update(@PathVariable(name = "id") Long id,
                @RequestBody @Valid UpdateBookmarkRequest request) {
        UpdateBookmarkCommand cmd = new UpdateBookmarkCommand(id, request.title(), request.url());
        bookmarkService.update(cmd);
    }

    @GetMapping("/{id}")
    ResponseEntity<BookmarkDTO> findById(@PathVariable(name = "id") Long id) {
        return bookmarkService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id) {
        bookmarkService.delete(id);
    }
}
