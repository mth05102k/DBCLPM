/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.book;

import java.util.List;
import javafx.util.Pair;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;
import model.book.Publisher;

/**
 *
 * @author DELL
 */
public interface BookDAO {

    int deleteBook(int id);

    int deleteBookAuthor(int bookId, int authorId);

    int updateBook(Book Book);

    int updateBookStatus(int bookId, int status);

    int updateAuthor(Author Author);

    int updatePublisher(Publisher publisher, int bookId);

    Book getBook(int id);

    Publisher getBookPUB(int id);

    List<Author> getBookAU(int id);

    BookItem getBookIT(int id);

    Book getBookWithBookitemID(int bookItemId);

    Pair<BookItem, Book> getBookAllSttWithBookID(int bookid);

    Pair<BookItem, Book> getBooAllSTTWithBookItemID(int bookItemID);

    Author getAuthorById(int authorId);

    int getBookCounting();

    List<Book> getMultipleBook(int from, int to);

    int addBookAuthor(Author author, int bookId);
}
