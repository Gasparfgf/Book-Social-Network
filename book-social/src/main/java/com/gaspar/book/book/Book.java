package com.gaspar.book.book;

import com.gaspar.book.common.BaseEntity;
import com.gaspar.book.feedback.FeedBack;
import com.gaspar.book.history.BookTransactionHistory;
import com.gaspar.book.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<FeedBack> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate(){
        if(feedbacks == null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(FeedBack::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate * 10.0)/10.0;
    }
}
