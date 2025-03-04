package com.gaspar.book.feedback;

import com.gaspar.book.book.Book;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {
    public FeedBack toFeedBack(FeedbackRequest request){
        return FeedBack.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .id(request.bookId())
                        .archived(false) //not required and has no impact
                        .shareable(false) //not required and has no impact
                        .build()
                )
                .build();
    }

    public FeedbackResponse toFeedBackResponse(FeedBack feedBack, Integer id) {
        return FeedbackResponse.builder()
                .note(feedBack.getNote())
                .comment(feedBack.getComment())
                .ownFeedBack(Objects.equals(feedBack.getCreatedBy(),id))
                .build();
    }
}
