package com.develmagic.quellio.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import rii.solutions.campus.data.source.Credentials;
import com.develmagic.quellio.MainActivity;
import com.develmagic.quellio.R;
import com.develmagic.quellio.basket.Basket;
import com.develmagic.quellio.basket.ProductQuantity;

import java.io.InputStream;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class Product extends LinearLayout {

    private int id;
    private String name;
    private float price;
    private Bitmap image;

    public Product(Context context) {
        super(context);
    }

    public Product(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Product(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Product(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static Product newInstance(LayoutInflater inflater, long id, String title, float price, String imageUrl) {
        Product product = (Product) inflater.inflate(R.layout.products_list_product, null);

        TextView name = (TextView) product.findViewById(R.id.productname);
        name.setText(title);

        TextView priceText = (TextView) product.findViewById(R.id.productprice);
        priceText.setText(price + " EUR");

        ImageView imageView = (ImageView) product.findViewById(R.id.productimage);
        new DownloadImageTask(imageView, product).execute(Credentials.API_BASE_URL + imageUrl);

        product.id = (int) id;
        product.setPrice(price);
        product.setName(title);

        product.setOnClickListener(new AddToBasketListener(product));

        return product;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Product product;

        DownloadImageTask(ImageView bmImage, Product product) {
            this.bmImage = bmImage;
            this.product = product;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            product.setImage(result);
        }
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    private static class AddToBasketListener implements View.OnClickListener {

        private Product product;

        AddToBasketListener(Product product) {
            this.product = product;
        }

        @Override
        public void onClick(View v) {

            if (Basket.getInstance().getProductQuantityById(this.product.getId()) == null) {
                ProductQuantity productQuantity = new ProductQuantity(this.product);
                Basket.getInstance().add(productQuantity);
            } else {
                Basket.getInstance().getProductQuantityById(this.product.getId()).increment();
            }
            Basket.getInstance().updateUI();
            MainActivity.getInstance().getFinishOrder().setClickable(true);

            if (Basket.getInstance().size() == 0) {
                MainActivity.getInstance().getFinishOrder().setClickable(false);
            }
        }

    }

}
