package vn.edu.usth.librarybottomnav.ui.recycler;

public class ChildModelClass {
    private int id; // Book ID
    private int image;
    private String title;
    private String author;
    private String description;
    private String category;
    private String content;

    // Constructor with id
    public  ChildModelClass(int image, int id, String title, String author, String content, String category, String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
        this.content = content;
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for image
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

    // Getter and setter for title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter and setter for description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return description;
    }
    public void setContent(String description) {
        this.description = description;
    }

    // Getter and setter for category
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
