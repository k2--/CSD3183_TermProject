package com.example.group_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Quizdb";

    private static final String USERS_TABLE_NAME = "Users";
    private static final String QUIZ_TABLE_NAME = "Quizzes";

    private static final String ID_FIELD = "id";
    private static final String EMAIL_FIELD = "email";
    private static final String PWRD_FIELD = "password";
    private static final String SECURITY_FIELD = "security";
    private static final String QUIZID_FIELD   = "id";
    private static final String NAME_FIELD = "name";
    private static final String QST1_FIELD = "qst1";
    private static final String QST2_FIELD = "qst2";
    private static final String QST3_FIELD = "qst3";
    private static final String ANS1_FIELD = "ans1";
    private static final String ANS2_FIELD = "ans2";
    private static final String ANS3_FIELD = "ans3";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STR= "CREATE TABLE " + USERS_TABLE_NAME + "(" +
                ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMAIL_FIELD + " TEXT, " +
                PWRD_FIELD + " TEXT, " +
                SECURITY_FIELD + " TEXT " +
                ")";
        db.execSQL(CREATE_TABLE_STR);
        CREATE_TABLE_STR= "CREATE TABLE " + QUIZ_TABLE_NAME + "(" +
                QUIZID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_FIELD + " TEXT, " +
                QST1_FIELD + " TEXT, " +
                QST2_FIELD + " TEXT, " +
                QST3_FIELD + " TEXT, " +
                ANS1_FIELD + " TEXT, " +
                ANS2_FIELD + " TEXT, " +
                ANS3_FIELD + " TEXT " +
                ")";
        db.execSQL(CREATE_TABLE_STR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed & recreate table
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUIZ_TABLE_NAME);
        onCreate(db);
    }

    //add new record
    public void addUser(User record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ID_FIELD, record.getId());
        contentValues.put(EMAIL_FIELD, record.getEmail());
        contentValues.put(PWRD_FIELD, record.getPassword());
        contentValues.put(SECURITY_FIELD, record.getSecurity());
        db.insert(USERS_TABLE_NAME, null, contentValues);
        db.close();
    }

    //get all record
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + USERS_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User record = new User();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setEmail(cursor.getString(1));
                record.setPassword(cursor.getString(2));
                record.setSecurity(cursor.getString(3));
                // Adding contact to list
                list.add(record);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //get the single record by ID
    public User getUserByID(int id) {
        User record = null;
        if(id == -1) { return record; }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USERS_TABLE_NAME, new String[]{ID_FIELD, EMAIL_FIELD, PWRD_FIELD,
                        SECURITY_FIELD}, ID_FIELD + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()) {
                record = new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3));
            }
        }
        return record;
    }

    //get the single record by email
    public Boolean isUser(String email) {
        boolean isUser = false;
        if(email.isEmpty()) { return isUser; }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USERS_TABLE_NAME, new String[]{ID_FIELD, EMAIL_FIELD, PWRD_FIELD,
                        SECURITY_FIELD}, EMAIL_FIELD + "=?", new String[]{String.valueOf(email)},
                null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()) {
                isUser = true;
            }
        }
        return isUser;
    }

    //validate user by email & password
    public User validateUser(String email, String password) {
        User record = null;
        if(email.isEmpty() || password.isEmpty()) { return record; }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USERS_TABLE_NAME, new String[]{ID_FIELD, EMAIL_FIELD, PWRD_FIELD,
                        SECURITY_FIELD}, EMAIL_FIELD + "=? and " + PWRD_FIELD + "=? ", new String[]{email,password},
                null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()) {
                record = new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3));
            }
        }
        return record;
    }

    //validate user by email & password
    public User validateUserSecurity(String email, String security) {
        User record = null;
        if(email.isEmpty() || security.isEmpty()) { return record; }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USERS_TABLE_NAME, new String[]{ID_FIELD, EMAIL_FIELD, PWRD_FIELD,
                        SECURITY_FIELD}, EMAIL_FIELD + "=? and " + SECURITY_FIELD + "=? ", new String[]{email,security},
                null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()) {
                record = new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3));
            }
        }
        return record;
    }

    //update single record
    public int updateUser(User record) {
        int retVal;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, record.getId());
        contentValues.put(EMAIL_FIELD, record.getEmail());
        contentValues.put(PWRD_FIELD, record.getPassword());
        contentValues.put(SECURITY_FIELD, record.getSecurity());

        retVal = db.update(USERS_TABLE_NAME, contentValues, ID_FIELD + " =? ",
                new String[]{String.valueOf(record.getId())});
        db.close();
        return retVal;
    }

    // Delete single record
    public int deleteUser(User record) {
        int retVal;
        SQLiteDatabase db = this.getWritableDatabase();
        retVal = db.delete(USERS_TABLE_NAME, ID_FIELD + " = ?",
                new String[] { String.valueOf(record.getId())});
        db.close();
        return retVal;
    }


    //add new Quiz
    public void addQuiz(Quiz record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(QUIZID_FIELD, record.getId());
        contentValues.put(NAME_FIELD, record.getName());
        contentValues.put(QST1_FIELD, record.getQst_1());
        contentValues.put(QST2_FIELD, record.getQst_2());
        contentValues.put(QST3_FIELD, record.getQst_3());
        contentValues.put(ANS1_FIELD, record.getAns_1());
        contentValues.put(ANS2_FIELD, record.getAns_2());
        contentValues.put(ANS3_FIELD, record.getAns_3());
        db.insert(QUIZ_TABLE_NAME, null, contentValues);
        db.close();
    }

    //get all Quizzes
    public ArrayList<Quiz> getAllQuizzes() {
        ArrayList<Quiz> list = new ArrayList<Quiz>();
        String selectQuery = "SELECT * FROM " + QUIZ_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Quiz record = new Quiz();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setName(cursor.getString(1));
                record.setQst_1(cursor.getString(2));
                record.setQst_2(cursor.getString(3));
                record.setQst_3(cursor.getString(4));
                record.setAns_1(cursor.getString(5));
                record.setAns_2(cursor.getString(6));
                record.setAns_3(cursor.getString(7));
                list.add(record);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //get the single quiz by ID
    public Quiz getQuizByID(int id) {
        Quiz record = null;
        if(id == -1) { return record; }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(QUIZ_TABLE_NAME, new String[]{QUIZID_FIELD, NAME_FIELD, QST1_FIELD, QST2_FIELD,
                        QST3_FIELD, ANS1_FIELD, ANS2_FIELD, ANS3_FIELD}, QUIZID_FIELD + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()) {
                record = new Quiz(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            }
        }
        return record;
    }

    //update single quiz
    public int updateQuiz(Quiz record) {
        int retVal;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUIZID_FIELD, record.getId());
        contentValues.put(NAME_FIELD, record.getName());
        contentValues.put(QST1_FIELD, record.getQst_1());
        contentValues.put(QST2_FIELD, record.getQst_2());
        contentValues.put(QST3_FIELD, record.getQst_3());
        contentValues.put(ANS1_FIELD, record.getAns_1());
        contentValues.put(ANS2_FIELD, record.getAns_2());
        contentValues.put(ANS3_FIELD, record.getAns_3());

        retVal = db.update(QUIZ_TABLE_NAME, contentValues, QUIZID_FIELD + " =? ",
                new String[]{String.valueOf(record.getId())});
        db.close();
        return retVal;
    }

    // Delete single quiz
    public int deleteQuiz(Quiz record) {
        int retVal;
        SQLiteDatabase db = this.getWritableDatabase();
        retVal = db.delete(QUIZ_TABLE_NAME, QUIZID_FIELD + " = ?",
                new String[] { String.valueOf(record.getId())});
        db.close();
        return retVal;
    }
}
