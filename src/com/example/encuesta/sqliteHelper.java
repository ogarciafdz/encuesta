package com.example.encuesta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqliteHelper extends SQLiteOpenHelper{

	String sqlCreate = "CREATE TABLE Registro (ID INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, pregunta1 INTEGER, pregunta2 INTEGER, pregunta3 INTEGER)";
//	http://exequielc.wordpress.com/2012/04/08/primeros-pasos-con-sqlite-en-android/
	public sqliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("create", sqlCreate);
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS Registro");
		db.execSQL(sqlCreate);
	}

}
