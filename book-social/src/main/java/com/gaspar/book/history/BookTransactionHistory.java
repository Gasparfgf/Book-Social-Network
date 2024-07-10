package com.gaspar.book.history;

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
public class BookTransactionHistory extends BaseEntity {

    //user and book relationship

    private boolean returned;
    private boolean returnApproved;
}
