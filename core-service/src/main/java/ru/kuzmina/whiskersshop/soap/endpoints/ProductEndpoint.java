package ru.kuzmina.whiskersshop.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.kuzmina.whiskersshop.soap.soapProducts.GetAllProductsRequest;
import ru.kuzmina.whiskersshop.soap.soapProducts.GetAllProductsResponse;
import ru.kuzmina.whiskersshop.soap.soapProducts.GetProductByIdRequest;
import ru.kuzmina.whiskersshop.soap.soapProducts.GetProductByIdResponse;
import ru.kuzmina.whiskersshop.soap.services.SoapProductService;

/*
//получение списка товаров
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.kuzmina.ru/wiskersshop/soapProducts">
    <soapenv:Header/>
    <soapenv:Body>
        <f:getAllProductsRequest/>
    </soapenv:Body>
</soapenv:Envelope>

//получение товара по id
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.kuzmina.ru/wiskersshop/soapProducts">
    <soapenv:Header/>
    <soapenv:Body>
        <f:getProductByIdRequest>
            <f:id>1</f:id>
        </f:getProductByIdRequest>
    </soapenv:Body>
</soapenv:Envelope>

*/

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.kuzmina.ru/wiskersshop/soapProducts";
    private final SoapProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request){
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getProductById(request.getId()));
        return response;
    }
}
