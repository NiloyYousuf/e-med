package com.example.alarm;

import java.util.ArrayList;

public  class cart {
    public static int total_items_selected = 0;
    public static Double Total_Amount = 0.00;
    public static ArrayList<Product> Products = new ArrayList<Product>();

    // create a new ArrayList to hold the non-duplicate products
    ArrayList<Product> nonDuplicateProducts = new ArrayList<>();

    public static void removeProductByName(String product_name) {
        for (int i = 0; i < Products.size(); i++) {
            if (Products.get(i).getProduct_Name().equals(product_name)) {
                Products.remove(i);
                break;
            }
        }
    }




    public void removeduplicatearraylist() {
// loop through the original ArrayList and remove duplicates while updating Addedtocart
        for (int i = 0; i < Products.size(); i++) {
            for (int j = i + 1; j < Products.size(); j++) {
                // if two products have the same Product_ID, remove the duplicate and update Addedtocart
                if (Products.get(i) != null && Products.get(j) != null && Products.get(i).Product_ID.equals(Products.get(j).Product_ID)) {
                    int addedToCart = Products.get(i).Addedtocart + Products.get(j).Addedtocart;
                    Products.get(i).Addedtocart = addedToCart;
                    Products.set(j, null);
                }
            }
        }

// loop through the original ArrayList and copy non-null products to the new ArrayList
        for (int i = 0; i < Products.size(); i++) {
            if (Products.get(i) != null) {
                nonDuplicateProducts.add(Products.get(i));
            }
        }

// update the original ArrayList to hold the non-duplicate products
        Products.clear();
        Products.addAll(nonDuplicateProducts);

        total_items_selected=0;
        for (Product product : Products) {
            total_items_selected += product.getAddedtocart();
        }
    }


    public static String generateSummary(ArrayList<Product> products) {
        // Create a new memo to hold the summary
        StringBuilder memo = new StringBuilder();

        // Initialize the total price to zero
        double totalPrice = 0;
        Total_Amount = (double) 0;
        total_items_selected = 0;
        // Loop through the products in the array and add their IDs and Addedtocart values to the memo
        for (Product product : products) {
            if (product.getAddedtocart() > 0) {
                memo.append("Product ID: " + product.getProduct_ID() + "\n");
                memo.append("Quantity: " + product.getAddedtocart() + "\n\n");

                // Add the price of the items to the total price
                totalPrice += Double.parseDouble(product.getProduct_Price()) * product.getAddedtocart();
                Total_Amount += Double.parseDouble(product.getProduct_Price()) * product.getAddedtocart();
                //total_items_selected+=Integer.parseInt(product.getAddedtocart());
            }

            // Add the total price to the memo
            memo.append("Total Order price: " + totalPrice);


            // Return the memo as a string
            System.out.println(memo.toString());
            return memo.toString();

        }


        return null;
    }
};
