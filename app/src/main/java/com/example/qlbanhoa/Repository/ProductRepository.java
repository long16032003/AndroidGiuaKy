package com.example.qlbanhoa.Repository;

import com.example.qlbanhoa.Entity.Product;

import java.util.ArrayList;

public class ProductRepository {
    private static ArrayList<Product> productList = new ArrayList<>();

    public ProductRepository(ArrayList<Product> lst) {
        for (Product p: lst){
            this.productList.add(p);
        }
    }
    public ProductRepository(){
    }

    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public static void setProductList(ArrayList<Product> productList) {
        ProductRepository.productList = productList;
    }

    public void addProduct(Product p){
        this.productList.add(p);
    }

    public Product getProduct(Integer id){
        Product result;
        for ( Product p : productList) {
            if (id == p.getId())
                return p;
        }
        return  null;
    }
}
