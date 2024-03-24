package com.example.qlbanhoa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qlbanhoa.Activity.CartActivity;
import com.example.qlbanhoa.Entity.Cart;
import com.example.qlbanhoa.Entity.Product;
import com.example.qlbanhoa.Entity.User;
import com.example.qlbanhoa.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> products;
    public Cart cart = new Cart();
    private Context mContext;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.mContext = context;
        this.products = products;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_product, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = products.get(position);
        if(p == null){
            return;
        }
        Glide.with(mContext)
                .load(p.getImage())
                .into(holder.imageProduct);
        holder.nameProduct.setText(p.getName());
        holder.descriptionProduct.setText((p.getDescription()));
        long price = p.getPrice();
        String priceString = String.valueOf(price);
        holder.priceProduct.setText("$"+priceString);

        holder.btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButtonClick(view, p);
            }
        });

    }

    private void addButtonClick(View view, Product p) {
        cart.addCart(p);
        showSnackbar(view, mContext.getString(R.string.add_product) + p.getName(), Snackbar.LENGTH_SHORT);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageProduct;
        private TextView nameProduct;
        private AppCompatButton btnAddtoCart;
        private TextView descriptionProduct;
        private TextView priceProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.imageProduct);
            nameProduct = (TextView) itemView.findViewById(R.id.nameProduct);
            btnAddtoCart = (AppCompatButton) itemView.findViewById(R.id.btnAddtoCart);
            descriptionProduct = (TextView) itemView.findViewById(R.id.descriptionProduct);
            priceProduct = (TextView) itemView.findViewById(R.id.priceProduct);
        }
    }
    public void showSnackbar(View view, String message, int duration)
    {
        Snackbar.make(view, message, duration).setAction("Cart", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                mContext.startActivity(intent);
            }
        }).show();
    }
}
