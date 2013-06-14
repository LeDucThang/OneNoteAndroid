package com.home.onenoteproject.models;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Note {
	private int id;
	private int accountId;
	private int notebookId;
	private int categoryId;
	private String title;
	private int favorite;
	private String createdAt;
	private String updatedAt;
	private SQLiteDatabase db;

	public static final String TABLE = "Note";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_ACCOUNTID = "accountId";
	public static final String COLUMN_NOTEBOOKID = "notebookId";
	public static final String COLUMN_CATEGORYID = "categoryId";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_FAVORITE = "favorite";
	public static final String COLUMN_CREATEDAT = "createat";
	public static final String COLUMN_UPDATEDAT = "updatedat";
	public static final String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS Note " +
			"( id INTEGER PRIMARY KEY, accountId INTEGER, notebookId INTEGER, categoryId Integer, title TEXT NOT NULL, " +
			"favorite INTEGER, createdAt TEXT, updatedAt TEXT);";
	
	public Note() {
		id = 0;
		accountId = 0;
		notebookId = 0;
		categoryId = 0;
		title = "";
		favorite = 0;
		createdAt = "";
		updatedAt = "";
		db = MyDatabase.db;
	}

	public List<Note> getAllNotes(int notebookId) {
		Cursor cursor = db.query(TABLE, new String[] { COLUMN_ID,
				COLUMN_ACCOUNTID, COLUMN_CATEGORYID, COLUMN_TITLE,
				COLUMN_FAVORITE, COLUMN_CREATEDAT, COLUMN_UPDATEDAT,
				COLUMN_NOTEBOOKID }, COLUMN_NOTEBOOKID + "=?",
				new String[] { "" + notebookId }, null, null, null);
		List<Note> list = new ArrayList<Note>();

		if (cursor.moveToFirst() == false)
			return list;

		do {
			Note note = new Note();
			note.id = cursor.getInt(0);
			note.accountId = cursor.getInt(1);
			note.categoryId = cursor.getInt(2);
			note.title = cursor.getString(3);
			note.favorite = cursor.getInt(4);
			note.createdAt = cursor.getString(5);
			note.updatedAt = cursor.getString(6);
			note.notebookId = cursor.getInt(7);
			list.add(note);
		} while (cursor.moveToNext());
		cursor.close();
		return list;
	}

	public List<Note> getAllNotesWithCategory(int notebookId, int categoryId) {
		Cursor cursor = db.query(TABLE, new String[] { COLUMN_ID,
				COLUMN_ACCOUNTID, COLUMN_CATEGORYID, COLUMN_TITLE,
				COLUMN_FAVORITE, COLUMN_CREATEDAT, COLUMN_UPDATEDAT,
				COLUMN_NOTEBOOKID }, COLUMN_NOTEBOOKID + " =? and "
				+ COLUMN_CATEGORYID + " =?", new String[] { "" + notebookId,
				"" + categoryId }, null, null, null);
		List<Note> list = new ArrayList<Note>();

		if (cursor.moveToFirst() == false)
			return list;

		do {
			Note note = new Note();
			note.id = cursor.getInt(0);
			note.accountId = cursor.getInt(1);
			note.categoryId = cursor.getInt(2);
			note.title = cursor.getString(3);
			note.favorite = cursor.getInt(4);
			note.createdAt = cursor.getString(5);
			note.updatedAt = cursor.getString(6);
			note.notebookId = cursor.getInt(7);
			list.add(note);
		} while (cursor.moveToNext());
		cursor.close();
		return list;
	}

	public boolean getNote(int noteId) {
		Cursor cursor = db.query(TABLE, new String[] { COLUMN_ID,
				COLUMN_ACCOUNTID, COLUMN_CATEGORYID, COLUMN_TITLE,
				COLUMN_FAVORITE, COLUMN_CREATEDAT, COLUMN_UPDATEDAT,
				COLUMN_NOTEBOOKID },
				COLUMN_ID + "=" + noteId, null, null, null, null);

		if (cursor.moveToFirst() == false)
			return false;

		id = cursor.getInt(0);
		accountId = cursor.getInt(1);
		categoryId = cursor.getInt(2);
		title = cursor.getString(3);
		favorite = cursor.getInt(4);
		createdAt = cursor.getString(5);
		updatedAt = cursor.getString(6);
		notebookId = cursor.getInt(7);

		return true;
	}

	public boolean saveNote() {
		ContentValues values = new ContentValues();
		values.put(COLUMN_CREATEDAT, createdAt);
		values.put(COLUMN_FAVORITE, favorite);
		values.put(COLUMN_NOTEBOOKID, notebookId);
		values.put(COLUMN_TITLE, title);
		values.put(COLUMN_UPDATEDAT, updatedAt);

		if (id == 0) {
			id = (int) db.insert(TABLE, null, values);
		} else {
			values.put(COLUMN_ID, id);
			id = db.update(TABLE, values, COLUMN_ID + "=" + id, null);
		}
		if (id <=0)
			return false;
		return true;
	}

	public void deleteNote(Note note) {
		db.delete(TABLE, COLUMN_ID + "=" + id, null);
		id = 0;
		accountId = 0;
		notebookId = 0;
		categoryId = 0;
		title = "";
		favorite = 0;
		createdAt = "";
		updatedAt = "";
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

	public int getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(int notebookId) {
		this.notebookId = notebookId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
