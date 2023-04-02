package com.example.alarm;

public class orderedItem {

    public String product_name ;
    public String product_description ;
    public String product_ID;
    public String product_selected;
    public String product_price;
    public String product_image_url ;
    public Integer selected;

    public orderedItem()
    {

    }
    public orderedItem(  String Product_ID,String Product_Name ,String Product_Price,String Product_Total_Available,String Product_Description,String Product_Image_URL) {

        this.product_ID=Product_ID;
        this.product_name = Product_Name;
        this.product_description =Product_Description;
        this.product_selected =Product_Total_Available;
        this.product_price =Product_Price;
        this.product_image_url =Product_Image_URL;
        this.selected=0;
    }




    public  String getProduct_description() {
        return product_description;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public  String getProduct_image_url() {
        return product_image_url;
    }

    public  String getProduct_name() {
        return product_name;
    }

    public  String getProduct_price() {
        return product_price;
    }

    public String getProduct_selected() {
        return product_selected;
    }
}
