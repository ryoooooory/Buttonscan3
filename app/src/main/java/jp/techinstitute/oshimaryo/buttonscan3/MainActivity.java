package jp.techinstitute.oshimaryo.buttonscan3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyHelper mHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new MyHelper(this);

        Button button = (Button) findViewById(R.id.button);
        final EditText edittext = (EditText) findViewById(R.id.editText);
        final TextView textView = (TextView) findViewById(R.id.textView);


        textView.setText("こんにちわ");


        button.setOnClickListener(new View.OnClickListener() {      //クリック時のイベント
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                String inputtext = edittext.getText().toString(); //ここに押された時の処理を記述
                ContentValues insertValues = new ContentValues();
                insertValues.put("name", inputtext);
                long id = db.insert("memo", inputtext, insertValues);            //挿入.第一引数はtable名、第二引数はdefault値、第三引数は入れる内容

                db.close();

            }
        });


        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {      //クリック時のイベント
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                Cursor c = db.query("memo", new String[]{"name"}, null, null, null, null, null);
                c.moveToFirst();
                String get = c.getString(0);
                textView.setText("こんにちわ" + get);


                db.close();

            }
        });


    }
}
//    private void insert(String memo) {
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//// 列に対応する値をセットする
//        ContentValues values = new ContentValues();
//        values.put(MyHelper.Columns.MEMO, memo);
//        values.put(MyHelper.Columns.CREATE_TIME, System.currentTimeMillis());
//        values.put(MyHelper.Columns.UPDATE_TIME, System.currentTimeMillis());
//// データベースに行を追加する
//        long id = db.insert(mHelper.TABLE_NAME, null, values);
//        if (id == -1) {
//            Log.v("Database", "行の追加に失敗したよ");
//        }
//// データベースを閉じる（処理の終了を伝える）
//        db.close();
//
//
//    }
//    private void updateMemo(int id, String memo) {
//// 1. SQLiteDatabase取得
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//// 2. 更新する値をセット
//        ContentValues values = new ContentValues();
//        values.put(MyHelper.Columns.MEMO, memo);
//        values.put(MyHelper.Columns.UPDATE_TIME, System.currentTimeMillis());
//// 更新する行をWHEREで指定
//        String where = MyHelper.Columns._ID + "=?";
//        String[] args = { String.valueOf(id) };
//        int count = db.update(MyHelper.TABLE_NAME, values, where, args);
//        if (count == 0) {
//            Log.v("Edit", "Failed to update");
//        }
//// 3. データベースを閉じる
//        db.close();
//    }
//}


