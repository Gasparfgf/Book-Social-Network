package com.gaspar.book.history;

import com.gaspar.book.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookTransactionalHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {
    @Query("""
        select history
        from BookTransactionHistory history
        where history.user.id = :userId
""")
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);
}
