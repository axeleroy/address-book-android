package com.axeleroy.adressbook;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Created by axeleroy on 29/01/2016.
 */
public class FileManager {
    final static String FILENAME = "adressbook.properites";

    public static void writeEntry(Entry entry, Context context) {
        FileOutputStream output = null;
        Properties prop = new Properties();
        prop.setProperty(entry.getSurname() + "." + entry.getLastname(), entry.getNumber());

        try {
            output = context.openFileOutput(FILENAME, Context.MODE_PRIVATE | Context.MODE_APPEND);
            prop.store(output, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<Entry> getEntries(Context context) {
        InputStream input = null;
        Properties prop = new Properties();
        List<Entry> entries = new ArrayList<Entry>();

        try {
            input = context.openFileInput(FILENAME);
            if (input != null) {
                prop.load(input);

                Enumeration<?> e = prop.propertyNames();
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    StringTokenizer st = new StringTokenizer(key, ".");

                    String surname = st.nextToken();
                    String lastname = st.nextToken();

                    String number = prop.getProperty(key);

                    entries.add(new Entry(lastname, surname, number));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

        return entries;
    }
}
