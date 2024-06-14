/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.datatransfer;

import com.cooptest.model.Product;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class ProductDto {
    private static Product data;
    private static ArrayList<Product> datas;

    public static Product getData() {
        return data;
    }

    public static void setData(Product data) {
        ProductDto.data = data;
    }

    public static ArrayList<Product> getDatas() {
        return datas;
    }

    public static void setDatas(ArrayList<Product> datas) {
        ProductDto.datas = datas;
    }
}
