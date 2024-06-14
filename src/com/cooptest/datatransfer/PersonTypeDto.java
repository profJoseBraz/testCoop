/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.datatransfer;

import com.cooptest.model.PersonType;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class PersonTypeDto {
    private static PersonType data;
    private static ArrayList<PersonTypeDto> datas;

    public static PersonType getData() {
        return data;
    }

    public static void setData(PersonType data) {
        PersonTypeDto.data = data;
    }

    public static ArrayList<PersonTypeDto> getDatas() {
        return datas;
    }

    public static void setDatas(ArrayList<PersonTypeDto> datas) {
        PersonTypeDto.datas = datas;
    }
}
