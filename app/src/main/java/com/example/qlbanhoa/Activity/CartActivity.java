package com.example.qlbanhoa.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlbanhoa.Adapter.CartAdapter;
import com.example.qlbanhoa.Entity.Cart;
import com.example.qlbanhoa.R;
import com.example.qlbanhoa.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rcvCart;
    private TextView tvTotal;
    private Cart cart = new Cart();
    private ActivityCartBinding binding;

    ImageView back_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvTotal = (TextView) findViewById(R.id.tvTotal);
        rcvCart = (RecyclerView) findViewById(R.id.rcvCart);

        back_main = (ImageView) findViewById(R.id.backMain);
        back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rcvCart.setLayoutManager(mLayoutManager);

        CartAdapter rvAdapter  = new CartAdapter(this, this.cart);
        rcvCart.setAdapter(rvAdapter);
        tvTotal.setText(""+this.cart.getTotalPrice());
    }

    public void updateData(){
        tvTotal.setText("" + this.cart.getTotalPrice());
    }
}