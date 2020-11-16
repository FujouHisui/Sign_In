package tech.hisui.sign_in;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " (" +
                    NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY, " +
                    NewsContract.NewsEntry.COLUMN_NAME_TITLE + " VARCHAR(200), " +
                    NewsContract.NewsEntry.COLUMN_NAME_AUTHOR + " VARCHAR(100), " +
                    NewsContract.NewsEntry.COLUMN_NAME_CONTENT + " TEXT, " +
                    NewsContract.NewsEntry.COLUMN_NAME_IMAGE + " VARCHAR(100) " +
                    ")" ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "news.db";

    private Context mContext;

}
