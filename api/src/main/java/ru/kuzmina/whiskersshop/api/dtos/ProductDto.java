package ru.kuzmina.whiskersshop.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель продукта")
public class  ProductDto {

    @Schema(description = "Идентификатор продукта", required = true, example = "1")
    private Long id;
    @Schema(description = "Название продукта", required = true, maxLength = 500, minLength = 5,example = "Корм для стерилизованных кошек")
    private String title;
    @Schema(description = "Название продукта", maxLength = 500, example = "Корм для стерилизованных кошек с уткой и клюквой")
    private String description;
    @Schema(description = "Стоимость продукта",required = true, example = "1345.99")
    private BigDecimal price;
    private String categoryTitle;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductDto() {
    }
}
