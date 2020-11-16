package tech.hisui.sign_in;

public class MyDbOpenHelper extends SQLiteOpenHelper {

    public MyDbOpenHelper(Context context) {
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        initDb(sqLiteDatabase);
    }
}
