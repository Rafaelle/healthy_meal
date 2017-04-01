package com.empsoft.safe_meal.DB;

/**
 * Created by Rafaelle on 31/03/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "safe_meal_db";
    private static final int DB_VERSION = 1;

    private static Table profileTable = null;
    private static Table intoleranceTable = null;
    private static Table dietTable = null;


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getProfileTable().toSQL());
        db.execSQL(getIntoleranceTable().toSQL());
        db.execSQL(getDietTable().toSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static Table getProfileTable() {
        if (profileTable == null) {
            profileTable = new Table("profile")
                    .addColumn(new Table.Column("id", "INTEGER", true))
                    .addColumn(new Table.Column("name", "STRING"));
        }
        return profileTable;
    }

    public static Table getIntoleranceTable() {
        if (intoleranceTable == null) {
            intoleranceTable = new Table("intolerance")
                    .addColumn(new Table.Column("id", "INTEGER"))
                    .addColumn(new Table.Column("intolerance", "STRING"))
                    .addForeignKey(new Table.ForeignKey(getProfileTable().getName())
                            .addReference("id", "id")
                    );
        }
        return intoleranceTable;
    }

    public static Table getDietTable() {
        if (dietTable == null) {
            dietTable = new Table("diet")
                    .addColumn(new Table.Column("id", "INTEGER"))
                    .addColumn(new Table.Column("diet", "STRING"))
                    .addForeignKey(new Table.ForeignKey(getProfileTable().getName())
                            .addReference("id", "id")
                    );
        }
        return dietTable;
    }
}