package com.gaspar.book.feedback;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {

    private Double note;
    private String comment;
    private boolean ownFeedBack;
}
