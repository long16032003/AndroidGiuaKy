package com.example.qlbanhoa.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlbanhoa.Adapter.ProductAdapter;
import com.example.qlbanhoa.Entity.Cart;
import com.example.qlbanhoa.Entity.Product;
import com.example.qlbanhoa.R;
import com.example.qlbanhoa.Repository.ProductRepository;
import com.example.qlbanhoa.databinding.ActivityMainBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProductRepository productRepository;
    private Cart cart = new Cart();

    private RecyclerView rcvProduct;
    LinearProgressIndicator progressIndicator;
    private ProductAdapter productAdapter;
    private ArrayList<Product> listProduct;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView openCart = (ImageView) findViewById(R.id.openCart);
        Button btnLogout = findViewById(R.id.btnLogout);
        openCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"Đã đăng xuất",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        initUi();
        getListProductFromDB();
        initData();
    }

    private void initData(){

        databaseReference = FirebaseDatabase.getInstance().getReference("product");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProduct.clear();
                for (DataSnapshot itemSnapShot : snapshot.getChildren()) {
                    Product product = itemSnapShot.getValue(Product.class);
                    listProduct.add(product);
                }
                System.out.println(listProduct);
                productRepository = new ProductRepository(listProduct);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
    private void initUi() {
        rcvProduct = (RecyclerView) findViewById(R.id.rcvproduct);
        progressIndicator = (LinearProgressIndicator) findViewById(R.id.progress_bar);
        //set Layout
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rcvProduct.setLayoutManager(mLayoutManager);

        listProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(this,listProduct);
        rcvProduct.setAdapter(productAdapter);

    }
    private void getListProductFromDB() {
        databaseReference = FirebaseDatabase.getInstance().getReference("product");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProduct.clear();
                for(DataSnapshot itemSnapShot : snapshot.getChildren()){
                    Product product = itemSnapShot.getValue(Product.class);
                    listProduct.add(product);
                }
                productAdapter.notifyDataSetChanged();
                progressIndicator.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}