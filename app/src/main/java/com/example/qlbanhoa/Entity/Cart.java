package com.example.qlbanhoa.Entity;

import com.example.qlbanhoa.Repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    public static Map<Integer, Integer> cartList = new HashMap<>();
    private Object keys[];

    public ProductRepository productRepository = new ProductRepository();
    private static float  totalPrice;

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addCart (Product product){
        Integer quantity = cartList.getOrDefault(product.getId(), 0);
        if (quantity >= 10) return;
        cartList.put(product.getId(), quantity + 1);
        totalPrice += product.getPrice();
    }

    //return a product follow the position from the cart
    public Product getProductByOrder(int pos){
        keys = cartList.keySet().toArray();
        return productRepository.getProduct(Integer.parseInt(keys[pos].toString()));
    }
    public float getLinePrice (Product p){
        return p.getPrice() * cartList.getOrDefault(p.getId(), 0);
    }
    public float getLinePrice (int pid){
        Product p = productRepository.getProduct(pid);
        return cartList.get(pid) * p.getPrice();
    }

    public void removeCart(Product p){
        Integer quantity = cartList.getOrDefault(p.getId(), 0);
        if (quantity <= 1) {
            totalPrice -= p.getPrice();
            cartList.remove(p.getId()); // Loại bỏ sản phẩm khỏi giỏ hàng
            return;
        }
        cartList.put(p.getId(), quantity - 1);
        totalPrice -= p.getPrice();
    }
}
