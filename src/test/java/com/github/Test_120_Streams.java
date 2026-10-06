package com.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.streams.FindWithinArrayList;
import com.github.streams.Invoice;

public class Test_120_Streams {
    FindWithinArrayList findWithinArrayList;

    @BeforeEach
    void setUp() {
        List<Invoice> invoices = new ArrayList<>();

        invoices.add(
            new Invoice(0, 2000, 0)
        );
        invoices.add(
            new Invoice(2, 2001, 100)
        );
        invoices.add(
            new Invoice(4, 2002, 200)
        );
        invoices.add(
            new Invoice(8, 2003, 300)
        );

        findWithinArrayList = new FindWithinArrayList(invoices);
    }

    @Test
    void distinctElementsWithEqualIDsAreNotAllowed() {
        // Arrange.
        List<Invoice> invoices = new ArrayList<>();
        long id = 1717;

        invoices.add(
            new Invoice(id, 3000, 0)
        );
        invoices.add(
            new Invoice(id, 3001, 100)
        );

        // Act + Assert.
        Exception exception = assertThrows(
            RuntimeException.class,
            () -> {
                new FindWithinArrayList(invoices);
            }
        );

        String observed = exception.getMessage();

        String expected = "the passed-in 'invoices' contains distinct elements with equal IDs";
        assertEquals(expected, observed);
    }

    @Test
    void findByIdWithoutStreams_1() {
        // Arrange.
        long existentInvoiceId = 4;

        // Act.
        Invoice observed = findWithinArrayList.findByIdWithoutStreams(existentInvoiceId);

        // Assert.
        assertEquals(4, observed.id());
        assertEquals(2002, observed.year());
        assertEquals(200, observed.amount());
    }

    @Test
    void findByIdWithoutStreams_2() {
        // Arrange.
        long nonexistentInvoiceId = 17;

        // Act.
        Invoice observed = findWithinArrayList.findByIdWithoutStreams(nonexistentInvoiceId);

        // Assert.
        assertNull(observed);
    }

    @Test
    void findByIdUsingStreams_1() {
        // Arrange.
        long existentInvoiceId = 4;

        // Act.
        Invoice observed = findWithinArrayList.findByIdUsingStreams(existentInvoiceId);

        // Assert.
        assertEquals(4, observed.id());
        assertEquals(2002, observed.year());
        assertEquals(200, observed.amount());
    }

    @Test
    void findByIdUsingStreams_2() {
        // Arrange.
        long nonexistentInvoiceId = 17;

        // Act.
        Invoice observed = findWithinArrayList.findByIdUsingStreams(nonexistentInvoiceId);

        // Assert.
        assertNull(observed);
    }

    @Test
    void findIndexOf_1() {
        // Arrange.
        long existentInvoiceId = 4;

        // Act.
        int observedIdx = findWithinArrayList.findIndexOf(existentInvoiceId);

        // Assert.
        int expectedIdx = 2;
        assertEquals(expectedIdx, observedIdx);
    }

    @Test
    void findIndexOf_2() {
        // Arrange.
        long nonexistentInvoiceId = 17;

        // Act.
        int observedIdx = findWithinArrayList.findIndexOf(nonexistentInvoiceId);

        // Assert.
        int expectedIdx = -1;
        assertEquals(expectedIdx, observedIdx);
    }
}
