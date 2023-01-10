package ru.kuzmina.whiskersshop.listeners;

public class ProductPriceDecreaseListener implements Observer{

    @Override
    public void update(ProductSubject subject, Object args) {
        //послать сообщение об изменении цены пользователю
        System.out.println("Цена на отслеживаемый продукт изменилась");
    }
}
