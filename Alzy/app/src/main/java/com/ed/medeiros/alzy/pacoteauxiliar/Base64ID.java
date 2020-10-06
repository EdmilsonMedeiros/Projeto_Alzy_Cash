package com.ed.medeiros.alzy.pacoteauxiliar;

import android.util.Base64;

public class Base64ID {
    public static String codificarBase64(String emailCodificar){
        return Base64.encodeToString(emailCodificar.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }
    public static String decodificarBase64(String emailDecodificar){
        return new String(Base64.decode(emailDecodificar, Base64.DEFAULT));
    }
}