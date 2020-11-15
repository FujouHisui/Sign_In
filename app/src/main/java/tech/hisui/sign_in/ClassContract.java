package tech.hisui.sign_in;

public final class ClassContract {
    private  ClassContract(){}
    public static class NewsEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_news";
        public static final String COLUMN_NAME_CLASS_TITLE = "class_title";
        public static final String COLUMN_NAME_CLASS_ID = "class_id";
        public static final String COLUMN_NAME_TEAHCHER = "teacher";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_IMAGE = "image";
    }


}
