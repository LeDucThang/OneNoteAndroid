package com.home.onenoteproject.models;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Notebook {
	public int id;
	public int accountId;
	public String name;
	public int nNotes;
	public SQLiteDatabase db;
	
	public static final String TABLE = "Notebook";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_ACCOUNTID = "accountId";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_NNOTES = "nnotes";
	
	public static final String CREATE_TABLE = "CREATE TABLE if not exists Notebook " +
			"( id integer primary key, name text not null, nNotes integer, accountId integer );";
	
	public Notebook()
	{
		id = 0;
		accountId = 0;
		name = "";
		nNotes = 0;
		db = MyDatabase.db;
	}
	
	public List<Notebook> getAllNotebooks(int accountId)
	{
		Cursor cursor = db.query(TABLE, new String[] 
				{COLUMN_ID,
				COLUMN_NAME,
				COLUMN_NNOTES,
				COLUMN_ACCOUNTID
				}, COLUMN_ACCOUNTID + "=?", new String[] {""+accountId}, null, null, null);

		List<Notebook> list = new ArrayList<Notebook>();
		
		if (cursor.moveToFirst() == false)
			return list;
		
		do
		{
			Notebook Notebook = new Notebook();
			Notebook.id = cursor.getInt(0);
			Notebook.name = cursor.getString(1);
			Notebook.nNotes = cursor.getInt(2);
			Notebook.accountId = cursor.getInt(3);
			list.add(Notebook);
		}while (cursor.moveToNext());
		cursor.close();
		return list;
	}
	
	public boolean getNotebook(int NotebookId)
	{
		Cursor cursor = db.query(TABLE, new String[] 
				{COLUMN_ID,
				COLUMN_NAME,
				COLUMN_NNOTES,
				COLUMN_ACCOUNTID
				}, COLUMN_ID + "=?", new String[] {""+NotebookId}, null, null, null);		
		
		if (cursor.moveToFirst() == false)
			return false;
		
		id = cursor.getInt(0);
		name = cursor.getString(1);
		nNotes = cursor.getInt(2);
		accountId = cursor.getInt(3);

		return true;
	}
	
	public boolean saveNotebook()
	{
		ContentValues values = new ContentValues();
		values.put(COLUMN_ACCOUNTID, accountId);
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_NNOTES, nNotes);
		if(id == 0)
		{
			id = (int) db.insert(TABLE, null, values);
		}
		else
		{
			values.put(COLUMN_ID, id);
			id = db.update(TABLE, values, COLUMN_ID+"="+id, null);
		}
		
		if (id <=0)
			return false;
		else
			return true;
	}
	
	public void deleteNotebook()
	{
		db.delete(TABLE, COLUMN_ID+"="+id, null);
		id = 0;
		accountId = 0;
		name = "";
		nNotes = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getnNotes() {
		return nNotes;
	}

	public void setnNotes(int nNotes) {
		this.nNotes = nNotes;
	}
	
	
}
