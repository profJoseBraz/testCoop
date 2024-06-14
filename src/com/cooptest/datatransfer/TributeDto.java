/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.datatransfer;

import com.cooptest.model.Tribute;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class TributeDto {
    private static Tribute data;
    private static ArrayList<Tribute> datas;

    public static Tribute getData() {
        return data;
    }

    public static void setData(Tribute data) {
        TributeDto.data = data;
    }

    public static ArrayList<Tribute> getDatas() {
        return datas;
    }

    public static void setDatas(ArrayList<Tribute> datas) {
        TributeDto.datas = datas;
    }
}
