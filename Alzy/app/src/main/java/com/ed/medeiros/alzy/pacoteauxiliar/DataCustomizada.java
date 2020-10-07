package com.ed.medeiros.alzy.pacoteauxiliar;

import java.text.SimpleDateFormat;

public class DataCustomizada {

    public static String dataAtual (){
        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;
    }

    public static String mesAnoEscolhido(String data){
        String retornaData [] = data.split("/");
        String mes = retornaData[1];
        String ano = retornaData[2];
        String mesAno = mes + ano;
        return mesAno;
    }

}
