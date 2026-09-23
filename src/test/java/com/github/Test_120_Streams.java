package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.streams.FindWithinArrayList;
import com.github.streams.Transaction;

public class Test_120_Streams {
    FindWithinArrayList findWithinArrayList;

    @BeforeEach
    void setUp() {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(
            new Transaction(0, 2000, 0)
        );
        transactions.add(
            new Transaction(2, 2001, 100)
        );
        transactions.add(
            new Transaction(4, 2002, 200)
        );
        transactions.add(
            new Transaction(8, 2003, 300)
        );

        findWithinArrayList = new FindWithinArrayList(transactions);
    }

    @Test
    void findByIdWithoutStreams_1() {
        // Arrange.
        long existentTransactionId = 4;

        // Act.
        Transaction observed = findWithinArrayList.findByIdWithoutStreams(existentTransactionId);

        // Assert.
        assertEquals(4, observed.id());
        assertEquals(2002, observed.year());
        assertEquals(200, observed.amount());
    }

    @Test
    void findByIdWithoutStreams_2() {
        // Arrange.
        long nonexistentTransactionId = 17;

        // Act.
        Transaction observed = findWithinArrayList.findByIdWithoutStreams(nonexistentTransactionId);

        // Assert.
        assertNull(observed);
    }

    @Test
    void findByIdUsingStreams_1() {
        // Arrange.
        long existentTransactionId = 4;

        // Act.
        Transaction observed = findWithinArrayList.findByIdUsingStreams(existentTransactionId);

        // Assert.
        assertEquals(4, observed.id());
        assertEquals(2002, observed.year());
        assertEquals(200, observed.amount());
    }

    @Test
    void findByIdUsingStreams_2() {
        // Arrange.
        long nonexistentTransactionId = 17;

        // Act.
        Transaction observed = findWithinArrayList.findByIdUsingStreams(nonexistentTransactionId);

        // Assert.
        assertNull(observed);
    }

    @Test
    void findIndexOf_1() {
        // Arrange.
        long existentTransactionId = 4;

        // Act.
        int observedIdx = findWithinArrayList.findIndexOf(existentTransactionId);

        // Assert.
        int expectedIdx = 2;
        assertEquals(expectedIdx, observedIdx);
    }

    @Test
    void findIndexOf_2() {
        // Arrange.
        long nonexistentTransactionId = 17;

        // Act.
        int observedIdx = findWithinArrayList.findIndexOf(nonexistentTransactionId);

        // Assert.
        int expectedIdx = -1;
        assertEquals(expectedIdx, observedIdx);
    }
}
