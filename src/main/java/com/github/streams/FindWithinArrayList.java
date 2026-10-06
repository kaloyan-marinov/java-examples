package com.github.streams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class FindWithinArrayList {

    private List<Invoice> invoices;

    public FindWithinArrayList(List<Invoice> invoices) {
        // Validate that there are no duplicated invoice IDs.
        Set<Long> ids = new HashSet<>();
        for (Invoice t : invoices) {
            ids.add(t.id());
        }

        if (ids.size() < invoices.size()) {
            throw new RuntimeException("the passed-in 'invoices' contains distinct elements with equal IDs");
        }

        this.invoices = invoices;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Invoice findByIdWithoutStreams(long invoiceId) {
        Invoice invoice = null;

        for (Invoice t : this.invoices) {
            if (t.id() == invoiceId) {
                invoice = t;
                break;
            }
        }

        return invoice;
    }

    public Invoice findByIdUsingStreams(long invoiceId) {
        Invoice invoice = this.invoices
            .stream()
            .filter((t) -> t.id() == invoiceId)
            .findFirst()
            .orElse(null)
        ;

        return invoice;
    }

    public int findIndexOf(long invoiceId) {
        int idx =
            IntStream.range(0, this.invoices.size())
            .filter((i) -> this.invoices.get(i).id() == invoiceId)
            .findFirst()
            .orElse(-1)
        ;

        return idx;
    }
}
