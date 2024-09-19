package vn.edu.usth.librarybottomnav.ui.recycler;

public class ChildModelClass {
    private int image;
    private String title;
    private String author;
    private String description;

    public ChildModelClass(int image, String title, String author,String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

