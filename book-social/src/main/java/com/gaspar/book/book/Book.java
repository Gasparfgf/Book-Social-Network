package com.gaspar.book.book;

import com.gaspar.book.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn; //book identifier
    private String synopsis; //resume about the book
    // stored into a location in the server and not in db because of its size
    private String bookCover;
    private boolean archived;
    private boolean shareable;
}
