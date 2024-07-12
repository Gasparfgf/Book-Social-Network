package com.gaspar.book.feedback;

import com.gaspar.book.book.Book;
import com.gaspar.book.book.BookRepository;
import com.gaspar.book.exceptions.OperationNotPermittedException;
import com.gaspar.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedBackRepository;
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with the ID:: " + request.bookId()));
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You can not give feedback to an archived or not shareable book.");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(book.getOwner().getBooks(), user.getId())) {
            throw new OperationNotPermittedException("You can not give feedback to your own book.");
        }
        FeedBack feedBack = feedbackMapper.toFeedBack(request);
        return feedBackRepository.save(feedBack).getId();
    }
}
