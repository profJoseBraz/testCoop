/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.datatransfer;

import com.cooptest.model.SaleItems;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class SaleItemsDto {
    private static SaleItems data;
    private static ArrayList<SaleItems> datas;

    public static SaleItems getData() {
        return data;
    }

    public static void setData(SaleItems data) {
        SaleItemsDto.data = data;
    }

    public static ArrayList<SaleItems> getDatas() {
        return datas;
    }

    public static void setDatas(ArrayList<SaleItems> datas) {
        SaleItemsDto.datas = datas;
    }
}
