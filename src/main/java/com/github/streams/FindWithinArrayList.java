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
        for (Invoice invc : invoices) {
            ids.add(invc.id());
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

        for (Invoice invc : invoices) {
            if (invc.id() == invoiceId) {
                invoice = invc;
                break;
            }
        }

        return invoice;
    }

    public Invoice findByIdUsingStreams(long invoiceId) {
        Invoice invoice = invoices
            .stream()
            .filter((invc) -> invc.id() == invoiceId)
            .findFirst()
            .orElse(null)
        ;

        return invoice;
    }

    public int findIndexOf(long invoiceId) {
        int index =
            IntStream.range(0, invoices.size())
            .filter((idx) -> invoices.get(idx).id() == invoiceId)
            .findFirst()
            .orElse(-1)
        ;

        return index;
    }
}
