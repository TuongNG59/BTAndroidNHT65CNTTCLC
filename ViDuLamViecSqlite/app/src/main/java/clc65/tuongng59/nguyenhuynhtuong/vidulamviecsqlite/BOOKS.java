package clc65.tuongng59.nguyenhuynhtuong.vidulamviecsqlite;

public class BOOKS {
    int bookID;
    String bookName;
    int bookPage;
    float bookPrice;
    String bookDecription;

    public BOOKS(int bookID, String bookName, int bookPage, float bookPrice, String bookDecription) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookPage = bookPage;
        this.bookPrice = bookPrice;
        this.bookDecription = bookDecription;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPage() {
        return bookPage;
    }

    public void setBookPage(int bookPage) {
        this.bookPage = bookPage;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDecription() {
        return bookDecription;
    }

    public void setBookDecription(String bookDecription) {
        this.bookDecription = bookDecription;
    }
}
