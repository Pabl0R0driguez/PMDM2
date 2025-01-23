package android.example.aplicaciongestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioCRUD {
    private DataBase dbHelper;
    private SQLiteDatabase database;

    public UsuarioCRUD(Context context) {
        dbHelper = new DataBase(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertarUsuario(String nombre, String clave) {
        ContentValues values = new ContentValues();
        values.put(DataBase.COLUMN_NOMBRE, nombre);
        values.put(DataBase.COLUMN_CLAVE, clave);
        return database.insert(DataBase.TABLE_USUARIOS, null, values);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = database.query(DataBase.TABLE_USUARIOS, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBase.COLUMN_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.COLUMN_NOMBRE));
                String clave = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.COLUMN_CLAVE));
                usuarios.add(new Usuario(id, nombre, clave));
            }
            cursor.close();
        }
        return usuarios;
    }

    public boolean validarUsuario(String nombre, String clave) {
        String selection = DataBase.COLUMN_NOMBRE + "=? AND " + DataBase.COLUMN_CLAVE + "=?";
        String[] selectionArgs = {nombre, clave};

        Cursor cursor = database.query(DataBase.TABLE_USUARIOS, null, selection, selectionArgs, null, null, null);
        boolean existe = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        return existe;
    }

    public int actualizarUsuario(int id, String nombre, String clave) {
        ContentValues values = new ContentValues();
        values.put(DataBase.COLUMN_NOMBRE, nombre);
        values.put(DataBase.COLUMN_CLAVE, clave);
        String whereClause = DataBase.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        return database.update(DataBase.TABLE_USUARIOS, values, whereClause, whereArgs);
    }

    public int eliminarUsuario(int id) {
        String whereClause = DataBase.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        return database.delete(DataBase.TABLE_USUARIOS, whereClause, whereArgs);
    }
}
