package com.adjoda.cucumber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingServiceTest {

    @Test
    public void incorrectWeight() {
        ShippingService shippingService = new ShippingService();
        assertThrows(IllegalStateException.class, () -> shippingService.calculateShippingFee(-1));
    }

    @Test
    public void firstRangeWeight() {
        ShippingService shippingService = new ShippingService();
        assertEquals(5, shippingService.calculateShippingFee(1));
    }
}