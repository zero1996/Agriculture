package com.rjxy.agriculture.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库辅助类
 * */
public class DBHelp extends SQLiteOpenHelper{

	private static final String DB_NAME="client1.db";
	
	public DBHelp(Context context){
		super(context,DB_NAME,null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table sensor(_id Integer primary key autoincrement,co2 Integer,light Integer,airHumidity Integer,airTemperature Integer,soilHumidity Integer,soilTemperature Integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
