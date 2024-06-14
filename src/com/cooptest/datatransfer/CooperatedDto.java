/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.datatransfer;

import com.cooptest.model.Cooperated;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class CooperatedDto {
    private static Cooperated data;
    private static ArrayList<Cooperated> datas;

    public static Cooperated getData() {
        return data;
    }

    public static void setData(Cooperated data) {
        CooperatedDto.data = data;
    }

    public static ArrayList<Cooperated> getDatas() {
        return datas;
    }

    public static void setDatas(ArrayList<Cooperated> datas) {
        CooperatedDto.datas = datas;
    }
}
