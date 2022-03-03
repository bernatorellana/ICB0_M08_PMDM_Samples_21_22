package com.example.sqlite.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context mContext;

    public DatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            String script = ScriptHelper.loadScript(mContext, "script.sql");
            TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter(';');

            // Once per string to split
            splitter.setString(script);
            for (String s : splitter) {
                db.execSQL(s);
            }

/*            Log.d("BERNAT","onCreate DB");

            // Creació directa d'una taula
            db.execSQL("CREATE TABLE persona ( "+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT,  "+
                    "nom TEXT,  "+
                    "pes REAL  "+
                    ");" );

            //---------------------------
            // Inserim valors usant la comanda insert

            ContentValues cv=new ContentValues();
            cv.put("nom", "Paco Maroto");
            cv.put("pes", 55.6 );
            db.insert("persona", null, cv);
            //---------------------------
            cv=new ContentValues();
            cv.put("nom", "Joanet Pepet");
            cv.put("pes",75.6 );
            db.insert("persona", null, cv);
            //---------------------------
            // Inserció mitjançant SQL tradicional
            db.execSQL("insert into persona values(null, 'Maria Cano', 234.3)",
                    new String[]{});

            db.execSQL("insert into persona values(null, ?, ?)",
                    new String[]{"Maria Cano", "234.3"});
                    */
        }
        catch(Exception e){
            Log.e("BERNAT", e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("BERNAT", "Actualitzant la BD, es destruirà tot" );
        // Esborrat de la taula
        db.execSQL("DROP TABLE IF EXISTS persona");
        // Cridem a la creació
        onCreate(db);

    }
}
