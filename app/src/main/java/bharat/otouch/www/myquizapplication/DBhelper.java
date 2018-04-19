package bharat.otouch.www.myquizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by shaan on 21/09/2017.
 */

public class DBhelper extends SQLiteOpenHelper{
    private static final int DATBASE_VERSION = 1;
    private static final String DATABASE_NAME = "QUIZQUESTION";
    private static final String TABLE_NAME = "QUESTION";
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OP1 = "op1";
    private static final String KEY_OP2 = "op2";
    private static final String KEY_OP3 = "op3";
    private SQLiteDatabase database;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OP1 +" TEXT, "
                +KEY_OP2 +" TEXT, "+KEY_OP3+" TEXT)";
        db.execSQL(sql);
        addQuestions();
    }
    private void addQuestions()
    {
        Question q1=new Question("Which company is the largest manufacturer of network equipment?","CISCO","HP", "IBM", "CISCO");
        this.addQuestion(q1);
        Question q2=new Question("Which of the following is NOT an operating system?","SuSe", "Windows", "BIOS", "DOS");
        this.addQuestion(q2);
        Question q3=new Question("Which of the following is the fastest writable memory?","RAM","ROM", "FLASH","Register");
        this.addQuestion(q3);
        Question q4=new Question("Which of the following device regulates internet traffic?","Hub", "Router", "Bridge", "Hub");
        this.addQuestion(q4);
        Question q5=new Question("Which of the following is NOT an interpreted language?","Ruby","C++","Python","BASIC");
        this.addQuestion(q5);
    }
    private void addAnswer(){
       // Answer a1
    }
    public void addQuestion(Question quest) {
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OP1, quest.getOPTA());
        values.put(KEY_OP2, quest.getOPTB());
        values.put(KEY_OP3, quest.getOPTC());
// Inserting Row
        database.insert(TABLE_NAME, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
// Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        database=this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
