package com.home.onenoteproject.models;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
	private MyDatabaseHelper dbHelper;
	public static SQLiteDatabase db;
	
	
	public MyDatabase(Context context)
	{
		dbHelper = new MyDatabaseHelper(context);
	}
	
	public void open() throws SQLException 
	{
		db = dbHelper.getWritableDatabase();
	}

	public void close() 
	{
		dbHelper.close();
	}
}
