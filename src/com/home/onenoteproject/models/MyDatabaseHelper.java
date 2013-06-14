package com.home.onenoteproject.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static String DATABASE_NAME = "OneNote";
	public static final int DATABASE_VERSION = 1;
	
	
	public MyDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(Account.CREATE_TABLE);
		db.execSQL(Notebook.CREATE_TABLE);
		db.execSQL(Note.CREATE_TABLE);
		db.execSQL(Category.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS OneNote");
		onCreate(db);
	}

}
