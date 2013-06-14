package com.home.onenoteproject.models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class Account {
	private int id;
	private String email;
	private String password;
	private String displayName;
	private int remember;
	
	private SQLiteDatabase db;
	
	public static final String TABLE = "Account";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_DISPLAYNAME = "displayname";
	public static final String COLUMN_REMEMBER = "remember";
	
	public static final String CREATE_TABLE = "create table if not exists Account ( id integer primary key," +
			" displayName text not null, username text not null, password text not null, remember integer);";
	
	public Account(){
		id = 0;
		email = "";
		password = "";
		displayName = "";
		remember = 0;
		db = MyDatabase.db;
	}
	
	// code encrypt SHA1 from http://stackoverflow.com/questions/4895523/java-string-to-sha1
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	// end code encrypt sha1
	
	
	public boolean setPassword(String password){
		boolean msg = false;
		if (password.trim().equals(""))
			msg = false;
		else {
			msg = true;
			this.password = encryptPassword(password);
		}
			
		return msg;
	}

	public boolean getRememberAccount(){
		Cursor cursor = db.query(TABLE, new String[]{
				COLUMN_ID,
				COLUMN_EMAIL,
				COLUMN_PASSWORD,
				COLUMN_DISPLAYNAME,
				COLUMN_REMEMBER
				}, COLUMN_REMEMBER + "= ?", new String[] {"1"}, null, null, null);
		
		if (cursor.moveToFirst() == true)
		{
			id = cursor.getInt(0);
			email = cursor.getString(1);
			password = cursor.getString(2);
			displayName = cursor.getString(3);
			remember = cursor.getInt(4);
			cursor.close();
		}
		if (id == 0)
			return false;
		else
			return true;
	}
	
	
	public boolean getAccount(String username){
		Cursor cursor = db.query(TABLE, new String[]{
				COLUMN_ID,
				COLUMN_EMAIL,
				COLUMN_PASSWORD,
				COLUMN_DISPLAYNAME,
				COLUMN_REMEMBER
				}, COLUMN_EMAIL + " = '" + username + "'" , null, null, null, null);
		
		
		if (cursor.moveToFirst() == true)
		{
			id = cursor.getInt(0);
			email = cursor.getString(1);
			password = cursor.getString(2);
			displayName = cursor.getString(3);
			remember = cursor.getInt(4);
			cursor.close();
		}
		if (id == 0)
			return false;
		else
			return true;
	}
	
	public boolean getAccount(int accountId){
		Cursor cursor = db.query(TABLE, new String[]{
				COLUMN_ID,
				COLUMN_EMAIL,
				COLUMN_PASSWORD,
				COLUMN_DISPLAYNAME,
				COLUMN_REMEMBER
				}, COLUMN_ID + "=" + accountId, null, null, null, null);
		
		if (cursor.moveToFirst() == true)
		{
			id = cursor.getInt(0);
			email = cursor.getString(1);
			password = cursor.getString(2);
			displayName = cursor.getString(3);
			remember = cursor.getInt(4);
			cursor.close();
		}
		if (id == 0)
			return false;
		else
			return true;
	}
	
	public boolean saveAccount()
	{
		ContentValues values = new ContentValues();
		values.put(COLUMN_EMAIL, email);
		values.put(COLUMN_PASSWORD, password);
		values.put(COLUMN_DISPLAYNAME, displayName);
		values.put(COLUMN_REMEMBER, remember);
		
		if (id == 0)
		{
			id = (int) db.insert(TABLE, null, values);
		}
		else
		{
			id = db.update(TABLE, values, "id="+id, null);
		}
		
		if (id <=0)
			return false;
		else 
			return true;
	}
	
	public void deleteAccount()
	{
		db.delete(TABLE, COLUMN_ID+"="+id, null);
		id = 0;
		email = "";
		password = "";
		displayName = "";
		remember = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getRemember() {
		return remember;
	}

	public void setRemember(int remember) {
		this.remember = remember;
	}

	public String getPassword() {
		return password;
	}
	
	
}
