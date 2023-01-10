package ru.kuzmina.whiskersshop.listeners;

public class ProductPriceDecreaseListener implements Observer {

    @Override
    public void update(ProductSubject subject, Object args) {
        //послать сообщение об изменении цены пользователю
        if (args instanceof PriceListenerArgs priceArgs) {
            System.out.printf("Цена на отслеживаемый продукт %s изменилась: %s", priceArgs.getProductTile(), priceArgs.getNewPrice().toString());
        }
    }
}
