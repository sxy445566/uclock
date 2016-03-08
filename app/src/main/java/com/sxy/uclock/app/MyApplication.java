package com.sxy.uclock.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.sxy.uclock.db.DaoMaster;
import com.sxy.uclock.db.DaoSession;

/**
 * Created by 30107 on 2016/3/8.
 */
public class MyApplication extends Application{
    /*** @Fields queue volley网络请求队列 */
//    public static RequestQueue mQueue;
//    /*** @Fields volleyErrorHelper velley网络请求帮助类 */
//    public static VolleyErrorHelper mVolleyErrorHelper;
//    public static ImageLoader mImageLoader;
    public static SQLiteDatabase db;
    public static DaoMaster daoMaster;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
//        mQueue = Volley.newRequestQueue(getApplicationContext());
//        mVolleyErrorHelper = new VolleyErrorHelper();
//        mImageLoader = new ImageLoader(mQueue, new BitMapCache());
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "uclock-db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
