package com.example.alarm;

public class Item
{
    private String product_name;
    private String product_description;
    private String product_ID;
    private String product_total_available;
    private String product_price;
    private String product_image_url;
    private  Integer selected;
    public Item(  String Product_ID,String Product_Name ,String Product_Price,String Product_Total_Available,String Product_Description,String Product_Image_URL) {

        this.product_ID=Product_ID;
        this.product_name = Product_Name;
        this.product_description =Product_Description;
        this.product_total_available =Product_Total_Available;
        this.product_price =Product_Price;
        this.product_image_url =Product_Image_URL;
        this.selected=0;
    }

    public Item()
    {

    }


    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public String getProduct_image_url() {
        return product_image_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_total_available() {
        return product_total_available;
    }

    public Integer getSelected()
    {
        return selected;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public void setProduct_total_available(String product_total_available) {
        this.product_total_available = product_total_available;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_image_url(String product_image_url) {
        this.product_image_url = product_image_url;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
}
