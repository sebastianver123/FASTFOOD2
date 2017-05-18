package co.edu.udistrtital.sebastianvergara.fastfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

class BaseDeDatos extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "Datos.db";

    private BaseDeDatos(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatosDeTabla.CREAR_TABLA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DatosDeTabla.SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    private static class DatosDeTabla implements BaseColumns {
        private static final String NOMBRE_TABLA = "datosIngreso";
        private static final String COLUMNA_ID = "id";
        private static final String COLUMNA_CORREO = "correo";
        private static final String COLUMNA_CONTRASEÑA = "contraseña";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String CREAR_TABLA =
                "CREATE TABLE " + DatosDeTabla.NOMBRE_TABLA + " (" +
                        DatosDeTabla.COLUMNA_ID + " INTEGER PRIMARY KEY," +
                        DatosDeTabla.COLUMNA_CORREO + TEXT_TYPE + COMMA_SEP +
                        DatosDeTabla.COLUMNA_CONTRASEÑA + TEXT_TYPE + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosDeTabla.NOMBRE_TABLA;

    }
}