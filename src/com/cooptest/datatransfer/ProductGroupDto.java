/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.datatransfer;

import com.cooptest.model.ProductGroup;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class ProductGroupDto {
    private static ProductGroup data;
    private static ArrayList<ProductGroup> datas;

    public static ProductGroup getData() {
        return data;
    }

    public static void setData(ProductGroup data) {
        ProductGroupDto.data = data;
    }

    public static ArrayList<ProductGroup> getDatas() {
        return datas;
    }

    public static void setDatas(ArrayList<ProductGroup> datas) {
        ProductGroupDto.datas = datas;
    }
}
