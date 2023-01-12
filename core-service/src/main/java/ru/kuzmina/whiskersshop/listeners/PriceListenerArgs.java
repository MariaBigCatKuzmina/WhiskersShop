package ru.kuzmina.whiskersshop.listeners;

import java.math.BigDecimal;

public class PriceListenerArgs {
    private String productTile;
    private Long productId;
    private BigDecimal newPrice;

    public PriceListenerArgs(String productTile, Long productId, BigDecimal newPrice) {
        this.productTile = productTile;
        this.productId = productId;
        this.newPrice = newPrice;
    }

    public String getProductTile() {
        return productTile;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }
}
