package com.backend.fog.facades;

import com.backend.fog.entities.Product;
import com.backend.fog.mappers.ProductMapper;
import com.backend.fog.persistence.DatabaseConnection;

public class ProductFacade {
    DatabaseConnection connection = new DatabaseConnection();
    ProductMapper productMapper = new ProductMapper();

    public void createNewProduct(String name, int mPrice, int length) {
        productMapper.createNewProduct(name, mPrice, length, connection);
    }

    public Product getBeam(int carportHeight) {
        return productMapper.getBeam(carportHeight, connection);
    }

    public Product getLongestSupportBeam(DatabaseConnection connection) {
        return productMapper.getLongestSupportBeam(connection);
    }

    public Product getSupportBeam(int carportLength) {
        return productMapper.getSupportBeam(carportLength, connection);
    }
}
