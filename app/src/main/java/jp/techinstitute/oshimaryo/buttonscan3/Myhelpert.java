package jp.techinstitute.oshimaryo.buttonscan3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by oshimaryo on 2018/08/13.
 */

class MyHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME ="memo";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    Columns._ID + " INTEGER primary key autoincrement," +
                    Columns.MEMO + " TEXT," +
                    Columns.CREATE_TIME + " INTEGER," +
                    Columns.UPDATE_TIME + " INTEGER)";
    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "mdb";
    /**
     * コンストラクタ
     */
    public MyHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }





    public interface Columns extends BaseColumns {
        public static final String MEMO = "name";
        public static final String CREATE_TIME = "create_time";
        public static final String UPDATE_TIME = "update_time";
    }
    /**
     * データベースファイルを作成すべき時に呼ばれる。
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
// CREATE文を実行する
        db.execSQL(SQL_CREATE_TABLE);
    }
    /**
     * データベースのバージョン（コンストラクタの第4引数）が
     * 変化した時に呼ばれる。
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// 現時点では何もしない
    }
}