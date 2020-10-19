package learn.mode.appventa.apiInterface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
        public static final String DATABASE_NAME= "product2.db";
        public static final String TABLE_NAME= "product_table2";
        public static final String COL_1= "ID";
        public static final String COL_2= "NAMEPRODUCTO";
        public static final String COL_3 = "PRECIOPRODUCTO";
        public static final String COL_4 = "CANTIDADOPRODUCTO";
        public static final String COL_5 = "IDCATEGORIA";
        public static final String COL_6 = "IDPRODUCTO";
        public static final String COL_7 = "IDUSUARIO";
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT " +
                    ", NAMEPRODUCTO TEXT" +
                    ", PRECIOPRODUCTO INTEGER" +
                    ", CANTIDADOPRODUCTO INTEGER" +
                    ", IDCATEGORIA INTEGER" +
                    ", IDPRODUCTO INTEGER" +
                    ", IDUSUARIO INTEGER)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData(String nameproducto , String precioproducto
                , String cantidadproducto, String idcategoria, String idproducto,String idusuario){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,nameproducto);
            contentValues.put(COL_3,precioproducto);
            contentValues.put(COL_4,cantidadproducto);
            contentValues.put(COL_5,idcategoria);
            contentValues.put(COL_6,idproducto);
            contentValues.put(COL_7,idusuario);
            long result = db.insert(TABLE_NAME,null,contentValues);
            if (result ==-1){
                return false;
            }else {
                return true;
            }
        }
        public Cursor getAllData(){
            SQLiteDatabase db= this.getWritableDatabase();
            Cursor c= db.rawQuery("Select * from "+ TABLE_NAME,null);
            return c;
        }
        public Integer Delete(String id){
            SQLiteDatabase db= this.getWritableDatabase();
            return db.delete(TABLE_NAME,"id = ?",new String[]{id});
        }
}
