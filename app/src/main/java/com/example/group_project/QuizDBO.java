package com.example.group_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizDBO extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Quizdb";
    private static final String QUIZ_TABLE_NAME = "Quizzes";

    private static final String QUIZID_FIELD   = "id";
    private static final String NAME_FIELD = "name";
    private static final String QST1_FIELD = "qst1";
    private static final String QST2_FIELD = "qst2";
    private static final String QST3_FIELD = "qst3";
    private static final String ANS1_FIELD = "ans1";
    private static final String ANS2_FIELD = "ans2";
    private static final String ANS3_FIELD = "ans3";

    public QuizDBO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STR= "CREATE TABLE " + QUIZ_TABLE_NAME + "(" +
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
        db.execSQL("DROP TABLE IF EXISTS " + QUIZ_TABLE_NAME);
        onCreate(db);
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
