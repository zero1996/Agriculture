package com.rjxy.agriculture.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

/**
 * 数据库基类
 * */
public abstract class BaseDB {

	DBHelp mDBHelp;
	DBHandler mDBHandler;
	
	public static final int SAVE_MSG = 0x1;
	public static final int LOAD_MSG = 0x2;
	
	private  OnDataBaseListener onDataBaseListener_save;
	private  OnDataBaseListener onDataBaseListener_load;
	
	/**
	 * 构造函数
	 * @param context
	 * 			上下文
	 * */
	public BaseDB(Context context){
		if(mDBHelp == null){
			mDBHelp = new DBHelp(context);
		}
		if(mDBHandler == null){
			mDBHandler = new DBHandler();
		}
		open();
	}
	
	public void open(){
		mDBHelp.getReadableDatabase();
		mDBHelp.getWritableDatabase();
	}
	
	/**
	 * 数据库回调接口
	 * */
	public interface OnDataBaseListener{
		void OnDatabase(ArrayList<Object> list,boolean isSuccess);
	}
	
	public void dellAllData(){
		mDBHelp.getWritableDatabase().delete(getTabName(), null, null);
	}
	
	/**
	 * 根据条件查询数据库取出数据
	 * */
	public void loadDataByTime(String selection,OnDataBaseListener onDataBaseListener_load){
		if(onDataBaseListener_load != null){
			this.onDataBaseListener_load = onDataBaseListener_load;
		}
		ArrayList<Object> list = new ArrayList<Object>();
		Cursor cursor = mDBHelp.getWritableDatabase().query(getTabName(), null, selection, null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				do{
					list.add(getDataByCursor(cursor));
				}while(cursor.moveToNext());
				Message msg = Message.obtain();
				msg.what = LOAD_MSG;
				msg.obj = list;
				mDBHandler.handleMessage(msg);
			}
		}
	}
	
	protected abstract Object getDataByCursor(Cursor cursor);
	
	
	/**
	 * 保存数据到数据库
	 * @param list
	 * 			传感器数据集合
	 * @param	listener
	 * 			回调接口
	 * */
	public void saveDataToDB(ArrayList<Object> list,OnDataBaseListener listener){
		if(listener != null){
			onDataBaseListener_save = listener;
		}
		final ArrayList<Object>  flist = list;
		if(flist!=null){
			 new Thread(){
				 public void run() {
					 super.run();
					 boolean success = false;
					 success = saveDataToEntry(flist);
					 Message msg = Message.obtain();
					 msg.what = SAVE_MSG;
					 msg.arg1 = success?1:0;
					 mDBHandler.handleMessage(msg);
				 };
			 }.start();
		}
	}
	
	/**
	 * 将数据插入数据库
	 * */
	private boolean saveDataToEntry(ArrayList<Object> list){
		boolean ret = false;
		if(list != null){
			for(int i=0;i<list.size();i++){
				ContentValues values = getContentByData(list.get(i));
				insertRows(values);
			}
			ret = true;
		}
		return ret;
	}
	
	protected abstract ContentValues getContentByData(Object object);
	
	/**
	 * 插入一行数据到数据库
	 * */
	public boolean insertRows(ContentValues values){
		boolean ret = false;
		if(values != null){
			mDBHelp.getWritableDatabase().insert(getTabName(), null, values);
			System.out.println("-------------插入成功");
			ret = true;
		}
		return ret;
	}
	
	/**
	 * 返回表名，由子类实现
	 * */
	protected abstract String getTabName();
	
	/**
	 * 消息处理类
	 * */
	class DBHandler extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
			case SAVE_MSG:
				boolean success = (msg.arg1)==1?true:false;
				if(onDataBaseListener_save != null){
					onDataBaseListener_save.OnDatabase(null, success);
				}
				break;
			case LOAD_MSG:
				ArrayList<Object> mlist = (ArrayList<Object>) msg.obj;
				if(onDataBaseListener_load != null){
					onDataBaseListener_load.OnDatabase(mlist, true);
				}
				break;
			}
		}
	}
}
