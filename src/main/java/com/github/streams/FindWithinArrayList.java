package com.github.streams;

import java.util.List;
import java.util.stream.IntStream;

public class FindWithinArrayList {

    private List<Transaction> transactions;

    public FindWithinArrayList(List<Transaction> transactions) {
        // Validate that there are no duplicated transaction IDs.

        this.transactions = transactions;
    }

    public Transaction findByIdWithoutStreams(long transactionId) {
        Transaction transaction = null;

        for (Transaction t : this.transactions) {
            if (t.id() == transactionId) {
                transaction = t;
                break;
            }
        }

        return transaction;
    }

    public Transaction findByIdUsingStreams(long transactionId) {
        Transaction transaction = this.transactions
            .stream()
            .filter((t) -> t.id() == transactionId)
            .findFirst()
            .orElse(null)
        ;

        return transaction;
    }

    public int findIndexOf(long transactionId) {
        int idx =
            IntStream.range(0, this.transactions.size())
            .filter((i) -> this.transactions.get(i).id() == transactionId)
            .findFirst()
            .orElse(-1)
        ;

        return idx;
    }
}
