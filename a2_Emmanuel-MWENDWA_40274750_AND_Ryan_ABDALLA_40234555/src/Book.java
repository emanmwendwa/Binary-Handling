import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    //attributes for the class
    protected String title;
    protected String author;
    protected double price;
    protected long isbn;
    protected int year;
    protected String genre;

    //constructor for book class
    public Book(String title, String author, double price, long isbn,String genre, int year){
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }
    //getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //equals method
    public boolean equals(Book bk) {
        if (bk == null || this.getClass() != bk.getClass()) return false;
        else {
            Book book = (Book) bk;
            return (this.title == book.title &&
                    this.author == book.author &&
                    this.price == book.price &&
                    this.isbn == book.isbn &&
                    this.genre == book.genre &&
                    this.year == book.year);}
    }



    //toString method

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", isbn=" + isbn +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                '}';
    }
}
