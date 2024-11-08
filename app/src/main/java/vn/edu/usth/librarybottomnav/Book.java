package vn.edu.usth.librarybottomnav;

public class Book {
    private int id;
    private String title;
    private String content;
    private String category;
    private String author;

    // Constructor
    public Book(int id, String title, String content, String category, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCategory() { return category; }
    public String getAuthor() { return author; }
}
