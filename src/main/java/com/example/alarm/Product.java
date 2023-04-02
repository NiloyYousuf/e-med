package com.example.alarm;

import java.util.ArrayList;

public class Product {
    public String getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public Integer getAddedtocart() {
        return Addedtocart;
    }

    String Product_ID;
    String Product_Name;
    String Product_Description;
    String Product_Total_Available;
    String Product_Price;
    String Product_Image_URL;
    Integer Addedtocart=0;

    public Product(String Product_ID,    String Product_Name,   String Product_Price ,    String Product_Total_Available,    String Product_Description,    String Product_Image_URL ,Integer Addedtocart)
    {
        this.Product_ID=Product_ID;
        this.Product_Name=Product_Name;
        this.Product_Description=Product_Description;
        this.Product_Total_Available=Product_Total_Available;
        this.Product_Price=Product_Price;
        this.Product_Image_URL=Product_Image_URL;
        this.Addedtocart=Addedtocart;
    }
    public  Product()
    {

    }

    public static Integer getAddedToCartValue(String productID) {
        for (Product product : cart.Products) {
            if (product.getProduct_ID().equals(productID)) {
                return product.getAddedtocart();
            }
        }
        return 0; // or you can return a default value if the productID doesn't exist in the productList
    }
}
