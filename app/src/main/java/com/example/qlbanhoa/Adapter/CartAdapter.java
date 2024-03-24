package com.example.qlbanhoa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qlbanhoa.Activity.CartActivity;
import com.example.qlbanhoa.Entity.Cart;
import com.example.qlbanhoa.Entity.Product;
import com.example.qlbanhoa.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Cart cart;
    private Context mContext;

    public CartAdapter(Context context, Cart cart) {
        this.mContext = context;
        this.cart = cart;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_cart, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final Integer pid = cart.getProductByOrder(position).getId();
        final Product p = cart.productRepository.getProduct(pid);

        String sProductName = p.getName();
        Integer amount = cart.cartList.get(pid);
        holder.txtProductName.setText(sProductName);
        holder.txtPrice.setText("$"+p.getPrice());
        Glide.with(mContext)
                .load(p.getImage())
                .into(holder.idIVSSImage);
        holder.edAmount.setText("" + amount);
        holder.tvLineTotal.setText("" + cart.getLinePrice(p));

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.addCart(p);
                Integer amount = cart.cartList.get(pid);
                holder.edAmount.setText(""+ amount );
                holder.tvLineTotal.setText("" + cart.getLinePrice(p));
                ((CartActivity)mContext).updateData();
            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.removeCart(p);
                Integer amount = cart.cartList.get(pid);
                holder.edAmount.setText(""+ amount );
                holder.tvLineTotal.setText("" + cart.getLinePrice(p));
                ((CartActivity)mContext).updateData();
            }
        });

    }

    @Override
    public int getItemCount() {
//        Log.d("CartAdapter", "" + cart.cartList.size());
        return cart.cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        public TextView txtProductName;
        public TextView txtPrice;
        public TextView tvLineTotal;
        public EditText edAmount;
        public TextView tvPlus;
        public TextView tvMinus;
        public LinearLayoutCompat relativeLayout;
        public ImageView idIVSSImage;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtProductName = (TextView)itemView.findViewById(R.id.idTVName);
            this.txtPrice = (TextView)itemView.findViewById(R.id.idTVPrice);
            this.idIVSSImage = (ImageView)itemView.findViewById(R.id.imageItemCart);
            this.relativeLayout = (LinearLayoutCompat) itemView.findViewById(R.id.llLayout);
            this.edAmount = (EditText) itemView.findViewById(R.id.amount);
            this.tvLineTotal = (TextView)itemView.findViewById(R.id.tvLineTotal);
            this.tvPlus = (TextView)itemView.findViewById(R.id.tvplus);
            this.tvMinus = (TextView)itemView.findViewById(R.id.tvminus);
        }
    }
}
