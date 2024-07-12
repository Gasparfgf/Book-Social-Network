package com.gaspar.book.feedback;

import com.gaspar.book.book.Book;
import com.gaspar.book.book.BookRepository;
import com.gaspar.book.common.PageResponse;
import com.gaspar.book.exceptions.OperationNotPermittedException;
import com.gaspar.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public PageResponse<FeedbackResponse> findAllFeedbackByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) connectedUser.getPrincipal());
        Page<FeedBack> feedBackPage =  feedBackRepository.findAllByBookId(bookId, pageable);
        List<FeedbackResponse> feedbackResponses = feedBackPage.stream()
                .map(feedBack -> feedbackMapper.toFeedBackResponse(feedBack, user.getId()))
                .toList();
        return new PageResponse<>(
                feedbackResponses,
                feedBackPage.getNumber(),
                feedBackPage.getSize(),
                feedBackPage.getTotalElements(),
                feedBackPage.getTotalPages(),
                feedBackPage.isFirst(),
                feedBackPage.isLast()
        );
    }
}
