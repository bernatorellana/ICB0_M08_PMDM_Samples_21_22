package com.example.sqlite.bd;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptHelper
{

    public static String loadScript(Context c, String assetsNameFile) {

        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(c.getAssets().open(assetsNameFile), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                sb.append(mLine);
            }
            return sb.toString();

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }

            }
        }
        return null;
    }
}
