/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.book;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;
import model.book.Publisher;

/**
 *
 * @author DELL
 */
public class BookDAOImpl implements BookDAO {

    private final Connection conn;

    private final String ADD_AUTHOR = "INSERT INTO author (name, biography, email, address) VALUES (?, ?, ?, ?)";
    private final String ADD_BOOK_AUTHOR = "INSERT INTO bookauthor (bookId, authorId) VALUES(?, ?)";

    private final String GET_BOOK = "SELECT * FROM book, publisher "
            + "WHERE book.id = ? "
            + "AND book.id = publisher.bookId";
    private final String GET_MULTIPLE_BOOK = "SELECT * FROM book LIMIT ?, ?";
    private final String GET_BOOK_PUBLISHER = "SELECT * FROM publisher WHERE BookID=?;";
    private final String GET_LIST_BOOK_AUTHOR = "Select author.ID,author.name,author.Biography,author.Email,author.address\n"
            + "FROM ((bookauthor "
            + "INNER JOIN book ON book.ID=bookauthor.BookID) "
            + "INNER JOIN author ON author.ID=bookauthor.AuthorID) "
            + "where book.ID=?;";
    private final String GET_BOOKITEM_WITH_BOOKITEMID = "SELECT * FROM bookitem where ID=?;";
    private final String GET_BOOKITEMID_WITH_BOOKID = "SELECT BookitemID FROM book where ID=?";
    private final String GET_BOOKID_WITH_BOOKITEMID = "SELECT ID FROM book where BookitemID=?";
    private final String GET_BOOK_WITH_BOOKITEMID = "SELECT * FROM book where bookItemId=?;";

    private final String GET_AUTHOR_BY_ID = "SELECT * FROM author WHERE id = ?";

    private final String UPDATE_BOOK = "UPDATE book "
            + "SET IBSN = ?, Tittle = ?, Sumary = ?, PublicationYear = ?, NumberOfPage = ?, Cost = ?, Language = ?, RemainingQuantity = ? "
            + "WHERE (ID = ?);";
    private final String UPDATE_BOOK_STATUS = "UPDATE book SET status = ? WHERE id = ?";
    private final String UPDATE_AUTHOR = "Update author SET Name=?,Biography=?,Email=?,Address=?" + "where ID=?";
    private final String UPDATE_PUBLISHER = "UPDATE publisher SET Name = ?, address = IFNULL(?, address) WHERE bookId = ?";

    private final String DELETE_PUBLISHER_WITH_BOOKID = "DELETE FROM publisher WHERE (BookID = ?);";
    private final String DELETE_BOOK_WITH_BOOKID = "DELETE FROM book WHERE id = ?;";
    private final String DELETE_BOOK_AUTHOR_WITH_BOOKID = "DELETE FROM bookauthor WHERE bookId = ? AND authorId = ?";
    private final String DELETE_AUTHOR = "DELETE FROM author WHERE id= ? ";

    private final String COUNT_BOOK = "SELECT COUNT(id) from book";

    public BookDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    private int deleteBookItem(int bookItemId) throws SQLException {
        String DELETE_BOOK_ITEM = "DELETE FROM bookitem WHERE id = ? ";

        PreparedStatement ps = conn.prepareStatement(DELETE_BOOK_ITEM);
        ps.setInt(1, bookItemId);

        int rowCount = ps.executeUpdate();

        return rowCount;
    }

    public int deletePublisher(int bookID) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(DELETE_PUBLISHER_WITH_BOOKID);
            ps.setInt(1, bookID);
            int rowDeleted = ps.executeUpdate();
            return rowDeleted;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int deleteBookAuthor(int bookId, int authorId) {
        int rowDeleted = 0;
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(DELETE_BOOK_AUTHOR_WITH_BOOKID);
            ps.setInt(1, bookId);
            ps.setInt(2, authorId);

            rowDeleted += ps.executeUpdate();

            ps = conn.prepareStatement(DELETE_AUTHOR);
            ps.setInt(1, authorId);

            rowDeleted += ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowDeleted;
        }
    }

    public int deleteAllBookAuthor(int bookId) {
        List<Author> listAuthor = getBookAU(bookId);
        int rowCount = 0;

        for (Author author : listAuthor) {
            try (PreparedStatement ps = conn.prepareStatement(DELETE_AUTHOR)) {
                ps.setInt(1, author.getId());
                rowCount += ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rowCount;
    }

    @Override
    public int deleteBook(int id) {
        int rowDeleted = 0;

        try {
            conn.setAutoCommit(false);

            rowDeleted = deleteAllBookAuthor(id);
            if (rowDeleted <= 0) {
                throw new SQLException("delete all book author failed");
            }

            rowDeleted = deletePublisher(id);
            if (rowDeleted <= 0) {
                throw new SQLException("delete book publisher failed");
            }

            int bookItemId = getBookItemId(id);
            if (bookItemId <= 0) {
                throw new SQLException("get bookItemId failed");
            }

            PreparedStatement ps = conn.prepareStatement(DELETE_BOOK_WITH_BOOKID);
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate();
            if (rowDeleted <= 0) {
                throw new SQLException("delete book failed");
            }

            rowDeleted = deleteBookItem(bookItemId);
            if (rowDeleted <= 0) {
                throw new SQLException("delete book item failed");
            }

            conn.commit();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowDeleted;
        }
    }

    @Override
    public int updateBook(Book book) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_BOOK)) {
            ps.setString(1, book.getIBSN());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getSummary());
            ps.setString(4, book.getPublicationYear());
            ps.setInt(5, book.getNumberOfPage());
            ps.setFloat(6, book.getCost());
            ps.setString(7, book.getLanguage());
            ps.setInt(8, book.getRemainingQuantity());
            ps.setInt(9, book.getId());

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }

    }

    @Override
    public int updateBookStatus(int bookId, int status) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_BOOK_STATUS)) {
            ps.setInt(1, status);
            ps.setInt(2, bookId);

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int updateAuthor(Author Author) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_AUTHOR)) {
            ps.setString(1, Author.getName());
            ps.setString(2, Author.getBiography());
            ps.setString(3, Author.getEmail());
            ps.setString(4, Author.getAddress());
            ps.setInt(5, Author.getId());

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int updatePublisher(Publisher publisher, int bookId) {
        int rowCount = 0;

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_PUBLISHER)) {
            ps.setString(1, publisher.getName());
            ps.setString(2, publisher.getAddress());
            ps.setInt(3, bookId);

            rowCount = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public int addBookAuthor(Author author, int bookId) {
        int rowCount = 0;

        try {
            PreparedStatement ps = conn.prepareStatement(ADD_AUTHOR, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getBiography());
            ps.setString(3, author.getEmail());
            ps.setString(4, author.getAddress());

            rowCount = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int authorId = rs.getInt(1);

                ps = conn.prepareStatement(ADD_BOOK_AUTHOR);
                ps.setInt(1, bookId);
                ps.setInt(2, authorId);

                rowCount += ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rowCount;
        }
    }

    @Override
    public Book getBook(int id) {
        Book book = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_BOOK)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = Mapper.mapBook(rs);
                book.setPub(Mapper.mapPublisher(rs));
                book.setAut(getBookAU(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return book;
        }
    }

    @Override
    public List<Book> getMultipleBook(int from, int to) {
        List<Book> listBook = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_MULTIPLE_BOOK)) {
            ps.setInt(1, from);
            ps.setInt(2, to);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = Mapper.mapBook(rs);
                listBook.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return listBook;
        }
    }

    public int getBookItemId(int bookId) {
        int bookItemId = -1;
        String GET_BOOK_ITEM_ID = "SELECT bookItemId FROM book WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(GET_BOOK_ITEM_ID)) {
            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bookItemId = rs.getInt("bookItemId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            return bookItemId;
        }
    }

    @Override
    public int getBookCounting() {
        int count = 0;

        try (PreparedStatement ps = conn.prepareStatement(COUNT_BOOK)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return count;
        }
    }

    @Override
    public Publisher getBookPUB(int bookId) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement(GET_BOOK_PUBLISHER);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            Publisher p = new Publisher();
            while (rs.next()) {
                p.setId(rs.getInt("publisher.id"));
                p.setAddress(rs.getString("publisher.address"));
                p.setName(rs.getString("publisher.name"));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Author> getBookAU(int bookId) {
        List<Author> listAuthor = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_LIST_BOOK_AUTHOR)) {
            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = Mapper.mapAuthor(rs);
                listAuthor.add(author);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return listAuthor;
        }
    }

    public int getBookItemIDWithBookID(int bookID) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement(GET_BOOKITEMID_WITH_BOOKID);
            ps.setInt(1, bookID);
            rs = ps.executeQuery();
            int bookitemID = 0;
            if (rs.next()) {
                bookitemID = rs.getInt("book.bookItemId");
            }
            return bookitemID;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public BookItem getBookIT(int bookitemID) {
        BookItem bookItem = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_BOOKITEM_WITH_BOOKITEMID)) {
            ps.setInt(1, bookitemID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bookItem = Mapper.mapBookItem(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return bookItem;
        }
    }

    @Override
    public Pair<BookItem, Book> getBookAllSttWithBookID(int bookId) {
        Book b = getBook(bookId);
        b.setPub(getBookPUB(bookId));
        b.setAut(getBookAU(bookId));
        int bookitemID = getBookItemIDWithBookID(bookId);
        Pair<BookItem, Book> tmp = new Pair(getBookIT(bookitemID), b);
        return tmp;
    }

    @Override
    public Book getBookWithBookitemID(int bookItemId) {
        Book book = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_BOOK_WITH_BOOKITEMID)) {
            ps.setInt(1, bookItemId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = Mapper.mapBook(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return book;
        }
    }

    @Override
    public Pair<BookItem, Book> getBooAllSTTWithBookItemID(int bookItemID) {
        Book b = getBookWithBookitemID(bookItemID);
        int bookID = b.getId();
        b.setPub(getBookPUB(bookID));
        b.setAut(getBookAU(bookID));
        Pair<BookItem, Book> tmp = new Pair(getBookIT(bookItemID), b);
        return tmp;
    }

    @Override
    public Author getAuthorById(int authorId) {
        Author author = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_AUTHOR_BY_ID)) {
            ps.setInt(1, authorId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = Mapper.mapAuthor(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return author;
        }
    }

}
