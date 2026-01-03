package com.levdev.bookmarks2.api.controllers;

import com.levdev.bookmarks2.domain.BookmarkDTO;
import com.levdev.bookmarks2.domain.BookmarkService;
import com.levdev.bookmarks2.domain.FindBookmarksQuery;
import com.levdev.bookmarks2.domain.PagedResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
