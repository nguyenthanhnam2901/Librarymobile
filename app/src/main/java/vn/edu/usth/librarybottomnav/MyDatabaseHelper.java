package vn.edu.usth.librarybottomnav;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.librarybottomnav.ui.recycler.ChildModelClass;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BookLibrary6.db";
    private static final int DATABASE_VERSION = 1;
    public static String TB_book = "book";
    public static String TB_author = "author";
    public static String TB_posts = "posts";
    public static String TB_notification = "notification";
    public static String TB_follows = "follows";
    public static String TB_category = "category";
    public static String TB_users = "users";

    public static String TB_book_id = "id";
    public static String TB_book_title = "title";
    public static String TB_book_category_id = "category_id";
    public static String TB_book_author_id = "author_id";
    public static String TB_book_content = "content";

    public static String TB_author_id = "id";
    public static String TB_author_name = "name";

    public static String TB_posts_id = "id";
    public static String TB_posts_title = "title";
    public static String TB_posts_body = "body";
    public static String TB_posts_user_id = "user_id";
    public static String TB_posts_created_at = "created_at";

    public static String TB_notification_id = "id";
    public static String TB_notification_sent_at = "sent_at";
    public static String TB_notification_account_id = "account_id";
    public static String TB_notification_body = "body";

    public static String TB_follows_following_user_id = "following_user_id";
    public static String TB_follows_user_follower_id = "user_follower_id";
    public static String TB_follows_following_book_id = "following_book_id";

    public static String TB_category_id = "id";
    public static String TB_category_name = "name";

    public static String TB_users_id = "users";
    public static String TB_users_gmail = "gmail";
    public static String TB_users_user_name = "user_name";
    public static String TB_users_password = "password";




    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tbCategory = "CREATE TABLE " + TB_category + " (" +
                TB_category_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TB_category_name + " TEXT NOT NULL);";

        String tbFollows = "CREATE TABLE " + TB_follows + " (" +
                TB_follows_following_book_id + " INTEGER, " +
                TB_follows_user_follower_id + " INTEGER, " +
                TB_follows_following_user_id + " INTEGER, " +
                "FOREIGN KEY (" + TB_follows_following_book_id + ") REFERENCES " + TB_users + "(" + TB_users_id + ")," +
                "FOREIGN KEY (" + TB_follows_user_follower_id + ") REFERENCES " + TB_users + "(" + TB_users_id + ")," +
                "FOREIGN KEY (" + TB_follows_following_user_id + ") REFERENCES " + TB_book + "(" + TB_book_id + "));";

        String tbUsers = "CREATE TABLE " + TB_users + " (" +
                TB_users_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TB_users_gmail + " TEXT NOT NULL, " +
                TB_users_password + " INTEGER NOT NULL, " +
                TB_users_user_name + " TEXT NOT NULL);";

        String tbPosts = "CREATE TABLE " + TB_posts + " (" +
                TB_posts_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TB_posts_title + " TEXT NOT NULL, " +
                TB_posts_body + " TEXT, " +
                TB_posts_user_id + " INTEGER NOT NULL, " +
                TB_posts_created_at + " DATE NOT NULL, " +
                "FOREIGN KEY (" + TB_posts_user_id + ") REFERENCES " + TB_users + "(" + TB_users_id + "));";

        String tbNotification = "CREATE TABLE " + TB_notification + " (" +
                TB_notification_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TB_notification_sent_at + " DATE NOT NULL, " +
                TB_notification_account_id + " INTEGER NOT NULL, " +
                TB_notification_body + " TEXT, " +
                "FOREIGN KEY (" + TB_notification_account_id + ") REFERENCES " + TB_users + "(" + TB_users_id + "));";

        String tbAuthor = "CREATE TABLE " + TB_author + " (" +
                TB_author_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TB_author_name + " TEXT); " ;


        String tbBook = "CREATE TABLE " + TB_book + " (" +
                TB_book_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TB_book_title + " TEXT, " +
                TB_book_category_id + " INTEGER NOT NULL, " +
                TB_book_author_id + " INTEGER NOT NULL, " +
                TB_book_content + " TEXT, " +
                "FOREIGN KEY (" + TB_book_author_id + ") REFERENCES " + TB_author + "(" + TB_author_id + ")," +
                "FOREIGN KEY (" + TB_book_category_id + ") REFERENCES " + TB_category + "(" + TB_category_id + "));";
        db.execSQL(tbBook);
        db.execSQL(tbCategory);
        db.execSQL(tbNotification);
        db.execSQL(tbUsers);
        db.execSQL(tbFollows);
        db.execSQL(tbPosts);
        db.execSQL(tbAuthor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_book);
        db.execSQL("DROP TABLE IF EXISTS " + TB_category);
        db.execSQL("DROP TABLE IF EXISTS " + TB_notification);
        db.execSQL("DROP TABLE IF EXISTS " + TB_users);
        db.execSQL("DROP TABLE IF EXISTS " + TB_follows);
        db.execSQL("DROP TABLE IF EXISTS " + TB_posts);
        db.execSQL("DROP TABLE IF EXISTS " + TB_author);
        onCreate(db);
    }

    public Cursor getBookById(String bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TB_book + " WHERE " + TB_book_id + " = ?", new String[]{bookId});
    }
    public String getAuthorName(int authorId) {
        String authorName = "Unknown";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TB_author_name + " FROM " + TB_author + " WHERE " + TB_author_id + " = ?", new String[]{String.valueOf(authorId)});
        if (cursor != null && cursor.moveToFirst()) {
            authorName = cursor.getString(0);
            cursor.close();
        }
        return authorName;
    }

    // Method to get the category's name by ID
    public String getCategoryName(int categoryId) {
        String categoryName = "Unknown";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TB_category_name + " FROM " + TB_category + " WHERE " + TB_category_id + " = ?", new String[]{String.valueOf(categoryId)});
        if (cursor != null && cursor.moveToFirst()) {
            categoryName = cursor.getString(0);
            cursor.close();
        }
        return categoryName;
    }
    public long insertAuthor(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_author_name, title);


        long resulta = db.insert(TB_author, null, values);
        return resulta;
    }



    public long insertUser(String gmail, String user_name, String password) {
        SQLiteDatabase db = this.getWritableDatabase(); // Open the database
        ContentValues values = new ContentValues();
        values.put(TB_users_gmail, gmail);
        values.put(TB_users_user_name, user_name);
        values.put(TB_users_password, password);
        long result = db.insert(TB_users, null, values);
        if (result == -1) {
            Log.e("MyDatabaseHelper", "Error inserting user data");
        } else {
            Log.d("MyDatabaseHelper", "User inserted successfully with ID: " + result);
        }
        return result;
    }

    public boolean isUserValid(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase(); // Open the database
        String query = "SELECT * FROM " + TB_users + " WHERE " + TB_users_user_name + " = ? AND " + TB_users_password + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    public void listAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase(); // Open the database
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_users, null);
        if (cursor.moveToFirst()) {
            do {
                String userName = cursor.getString(cursor.getColumnIndexOrThrow(TB_users_user_name));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(TB_users_gmail));
                String pass = cursor.getString(cursor.getColumnIndexOrThrow(TB_users_password));
                Log.d("MyDatabaseHelper", "User: " + userName + ", Email: " + email + ", Password: " + pass);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public int getCategoryId(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + TB_category_id + " FROM " + TB_category + " WHERE " + TB_category_name + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{categoryName});

        int categoryId = -1;  // Default value if category is not found
        if (cursor.moveToFirst()) {
            categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(TB_category_id));
        }
        cursor.close();

        return categoryId;
    }

    public int getAuthorId(String authorName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + TB_author_id + " FROM " + TB_author + " WHERE " + TB_author_name + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{authorName});

        int authorId = -1;
        if (cursor.moveToFirst()) {
            authorId = cursor.getInt(cursor.getColumnIndexOrThrow(TB_author_id));
        }
        cursor.close();
        return authorId;
    }


    public long insertBook(String title, String content, String category, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_book_title, title);
        values.put(TB_book_content, content);
        values.put(TB_book_category_id, getCategoryId(category));
        values.put(TB_book_author_id, getAuthorId(author));

        long result = db.insert(TB_book, null, values);
        return result;
    }

    public List<ChildModelClass> searchBooks(String title, String author, String category) {
        List<ChildModelClass> searchResults = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TB_book + " WHERE 1=1";
        List<String> args = new ArrayList<>();

        if (!title.isEmpty()) {
            query += " AND " + TB_book_title + " LIKE ?";
            args.add("%" + title + "%");
        }
        if (!author.isEmpty()) {
            query += " AND " + TB_book_author_id + " IN (SELECT " + TB_author_id + " FROM " + TB_author + " WHERE " + TB_author_name + " LIKE ?)";
            args.add("%" + author + "%");
        }
        if (!category.isEmpty()) {
            query += " AND " + TB_book_category_id + " IN (SELECT " + TB_category_id + " FROM " + TB_category + " WHERE " + TB_category_name + " LIKE ?)";
            args.add("%" + category + "%");
        }

        Cursor cursor = db.rawQuery(query, args.toArray(new String[0]));
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(TB_book_id));
                String bookTitle = cursor.getString(cursor.getColumnIndexOrThrow(TB_book_title));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(TB_book_content));

                // Retrieve the author and category names
                String bookAuthor = getAuthorName(cursor.getInt(cursor.getColumnIndexOrThrow(TB_book_author_id)));
                String bookCategory = getCategoryName(cursor.getInt(cursor.getColumnIndexOrThrow(TB_book_category_id)));

                // Assuming the 6th parameter is a description (you can adjust as needed)
                String placeholderDescription = "";  // Replace with the actual description or an empty string

                searchResults.add(new ChildModelClass(R.drawable.placeholder, id, bookTitle, bookAuthor, content, bookCategory, placeholderDescription));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return searchResults;
    }

    public Cursor getBookDetailsById(int bookId) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Log the bookId being queried
        System.out.println("DEBUG: Querying Book ID: " + bookId);

        // Ensure correct table and column names
        String query = "SELECT * FROM book WHERE id = id";
        return db.rawQuery(query, new String[]{String.valueOf(bookId)});
    }


    // MyDatabaseHelper.java
    public String getBookContent(int bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String content = null;

        // Query to fetch the book content based on the book ID
        Cursor cursor = db.rawQuery("SELECT content FROM book WHERE id = id", new String[]{String.valueOf(bookId)});

        if (cursor != null && cursor.moveToFirst()) {
            // Get the content column value
            content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            cursor.close();
        } else {
            System.err.println("ERROR: No content found for Book ID: " + bookId);
        }

        db.close();
        return content;
    }




}


