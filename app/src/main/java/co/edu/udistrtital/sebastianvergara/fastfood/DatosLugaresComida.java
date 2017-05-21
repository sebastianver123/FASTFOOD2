package co.edu.udistrtital.sebastianvergara.fastfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Sebastian on 20/05/2017.
 */

public class DatosLugaresComida extends SQLiteOpenHelper {

    public static final int VERSION_BASEDATOS = 1;
    public static final String NOMBRE_BASEDATOS = "DatosComida.db";

    DatosLugaresComida(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatosLugaresComida.DatosTabla.CREAR_TABLA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DatosLugaresComida.DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public static class DatosTabla implements BaseColumns {
        public static final String NOMBRE_TABLA = "datosLugares";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_LUGAR = "lugar";
        public static final String COLUMNA_LAT = "lat";
        public static final String COLUMNA_LNG = "lng";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String CREAR_TABLA =
                "CREATE TABLE " + DatosLugaresComida.DatosTabla.NOMBRE_TABLA + " (" +
                        DatosLugaresComida.DatosTabla.COLUMNA_ID+ " INTEGER PRIMARY KEY," +
                        DatosTabla.COLUMNA_LUGAR + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.COLUMNA_LAT + TEXT_TYPE +
                        DatosTabla.COLUMNA_LNG + TEXT_TYPE +" )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosLugaresComida.DatosTabla.NOMBRE_TABLA;

    }




}
