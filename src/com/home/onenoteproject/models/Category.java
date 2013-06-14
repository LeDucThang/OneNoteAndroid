package com.home.onenoteproject.models;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Category {
	private int id;
	private int accountId;
	private int notebookId;
	private String name;
	private SQLiteDatabase db;
	
	public static final String TABLE = "Category";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_ACCOUNTID = "accountId";
	public static final String COLUMN_NOTEBOOKID = "notebookId";
	public static final String COLUMN_NAME = "name";
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Category (id INTEGER PRIMARY KEY," +
			" accountId INTEGER, notebookId INTEGER, name text);";
	
	public Category()
	{
		id = 0;
		accountId = 0;
		notebookId = 0;
		name = "Tất cả";
		db = MyDatabase.db;
	}
	
	public List<Category> getAllCategories(int accountId) {
		Cursor cursor = db.query(TABLE, new String[] {COLUMN_ID,COLUMN_ACCOUNTID, COLUMN_NAME, COLUMN_NOTEBOOKID}, 
				COLUMN_ACCOUNTID + " =? ", new String[] {""+accountId}, null, null, null);
		
		List<Category> list = new ArrayList<Category>();
		if (cursor.moveToFirst() == false)
			return list;
		
		Category category = new Category();
		category.id = cursor.getInt(0);
		category.accountId = cursor.getInt(1);
		category.name = cursor.getString(2);
		category.notebookId = cursor.getInt(3);
		list.add(category);
		return list;
	}
	
	public List<Category> getAllCategoriesWithNotebook(int accountId, int notebookId) {
		Cursor cursor = db.query(TABLE, new String[] {COLUMN_ID,COLUMN_ACCOUNTID, COLUMN_NAME, COLUMN_NOTEBOOKID},
				COLUMN_ACCOUNTID + " =? and " + COLUMN_NOTEBOOKID + " =?" , 
				new String[] {""+accountId, ""+notebookId}, null, null, null);
		
		List<Category> list = new ArrayList<Category>();
		if (cursor.moveToFirst() == false)
			return list;
		
		Category category = new Category();
		category.id = cursor.getInt(0);
		category.accountId = cursor.getInt(1);
		category.name = cursor.getString(2);
		category.notebookId = cursor.getInt(3);
		list.add(category);
		return list;
	}
	
	public boolean getCategory(int categoryId) {
		Cursor cursor = db.query(TABLE, new String[] {COLUMN_ID,COLUMN_ACCOUNTID, COLUMN_NAME, COLUMN_NOTEBOOKID}, 
				COLUMN_ID + " =? ", new String[] {""+categoryId}, null, null, null);
		
		if (cursor.moveToFirst() == false)
			return false;
		id = cursor.getInt(0);
		accountId = cursor.getInt(1);
		name = cursor.getString(2);
		notebookId = cursor.getInt(3);
		return true;
	}
	
	public boolean saveCategory() {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ACCOUNTID, accountId);
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_NOTEBOOKID, notebookId);
		
		if (id == 0) {
			id = (int) db.insert(TABLE, null, values);
		}
		else {
			values.put(COLUMN_ID, id);
			id = db.update(TABLE, values, COLUMN_ID + " = " + id, null);
		}
		if (id <=0)
			return false;
		else
			return true;
	}
	
	public void deleteCategory()
	{
		db.delete(TABLE, COLUMN_ID + " = " +id, null);
		id = 0;
		accountId = 0;
		notebookId = 0;
		name = "Tất cả";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
