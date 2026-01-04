package com.levdev.bookmarks2.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("""
           SELECT new com.levdev.bookmarks2.domain.BookmarkDTO(
               b.id, b.title, b.url, b.createdAt, b.updatedAt
           )
           FROM Bookmark b
           """)
    Page<BookmarkDTO> findBookmarks(Pageable pageable);

    @Query("""
       SELECT new com.levdev.bookmarks2.domain.BookmarkDTO(
           b.id, b.title, b.url, b.createdAt, b.updatedAt
       )
       FROM Bookmark b
       WHERE b.id = :id
       """)
    Optional<BookmarkDTO> findBookmarkById(@Param("id") Long id);
}
