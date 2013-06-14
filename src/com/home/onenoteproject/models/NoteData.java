package com.home.onenoteproject.models;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteData {
	private int id;
	private int noteId;
	private int accountId;
	private int type;
	private String data;
	private String link;
	private String updatedAt;
	private SQLiteDatabase db;
	
	public static final int TEXT = 1;
	public static final int AUDIO = 2;
	public static final int VIDEO = 3;
	public static final int IMAGE = 4;
	public static final int LOCATION = 5;
	
	public static final String TABLE = "NoteData";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOTEID = "noteId";
	public static final String COLUMN_ACCOUNTID = "accountId";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_DATA = "data";
	public static final String COLUMN_LINK = "link";
	public static final String COLUMN_UPDATEDAT = "updatedAt";
	
	public NoteData() {
		id = 0;
		noteId = 0;
		accountId = 0;
		type = 0;
		data = "";
		link = "";
		updatedAt = "";
		db = MyDatabase.db;
	}
	
	public List<NoteData> getAllNoteData(int noteId){
		List<NoteData> list = new ArrayList<NoteData>();
		Cursor cursor = db.query(TABLE, 
				new String[] {COLUMN_ID, COLUMN_NOTEID, COLUMN_ACCOUNTID, 
				COLUMN_TYPE, COLUMN_DATA, COLUMN_LINK, COLUMN_UPDATEDAT},
				COLUMN_NOTEID + " = " + noteId, null, null, null, null);
		if (cursor.moveToFirst() == false)
			return list;
		
		NoteData noteData = new NoteData();
		noteData.id = cursor.getInt(1);
		noteData.noteId = cursor.getInt(2);
		noteData.accountId = cursor.getInt(3);
		noteData.type = cursor.getInt(4);
		noteData.data = cursor.getString(5);
		noteData.link = cursor.getString(6);
		noteData.updatedAt = cursor.getString(7);
		
		return list;
	}
	
	public boolean getNoteData(int noteDataId){
		Cursor cursor = db.query(TABLE, 
				new String[] {COLUMN_ID, COLUMN_NOTEID, COLUMN_ACCOUNTID, 
				COLUMN_TYPE, COLUMN_DATA, COLUMN_LINK, COLUMN_UPDATEDAT},
				COLUMN_ID + " = " + noteDataId, null, null, null, null);
		if (cursor.moveToFirst() == false)
			return false;
		
		id = cursor.getInt(1);
		noteId = cursor.getInt(2);
		accountId = cursor.getInt(3);
		type = cursor.getInt(4);
		data = cursor.getString(5);
		link = cursor.getString(6);
		updatedAt = cursor.getString(7);
		
		return true;
	}
	
	public boolean saveNoteData() {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ACCOUNTID, accountId);
		values.put(COLUMN_DATA, data);
		values.put(COLUMN_LINK,link);
		values.put(COLUMN_NOTEID, noteId);
		values.put(COLUMN_TYPE, type);
		values.put(COLUMN_UPDATEDAT, updatedAt);
		
		if (id == 0)
			id = (int) db.insert(TABLE, null, values);
		else
			id = db.update(TABLE, values, COLUMN_ID + " = " + id, null);
		
		if (id <=0)
			return false;
		else
			return true;
	}
	
	public void deleteNoteData() {
		db.delete(TABLE, COLUMN_ID + " = " + id, null);
		id = 0;
		noteId = 0;
		accountId = 0;
		type = 0;
		data = "";
		link = "";
		updatedAt = "";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
