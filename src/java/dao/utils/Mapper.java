/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;
import model.book.Publisher;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;
import model.user.Account;
import model.user.Address;
import model.user.FullName;
import model.user.User;

/**
 *
 * @author Admin
 */
public class Mapper {

    public static Address mapAddress(ResultSet rs) throws SQLException {
        int id = rs.getInt("address.id");
        String addressDetail = rs.getString("address.addressDetail");
        String district = rs.getString("address.district");
        String city = rs.getString("address.city");

        String shipmentDistrictId = rs.getString("address.shipmentDistrictId");
        String shipmentCityId = rs.getString("address.shipmentCityId");

        return new Address(id, addressDetail, district, city, shipmentDistrictId, shipmentCityId);
    }

    public static FullName mapFullName(ResultSet rs) throws SQLException {
        int id = rs.getInt("fullname.id");
        String firstName = rs.getString("fullname.firstname");
        String midName = rs.getString("fullname.midname");
        String lastName = rs.getString("fullname.lastname");

        return new FullName(id, firstName, midName, lastName);
    }

    public static Account mapAccount(ResultSet rs) throws SQLException {
        int id = rs.getInt("account.id");
        String username = rs.getString("account.username");
        String password = rs.getString("account.password");
        byte role = rs.getByte("account.role");

        return new Account(id, username, password, role);
    }

    public static User mapUser(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user.id");
        String phoneNumber = rs.getString("user.phone");
        String email = rs.getString("user.email");
        String gender = rs.getString("user.gender");
        String avatar = rs.getString("user.avatar");

        FullName fullName = mapFullName(rs);
        Address address = mapAddress(rs);
        Account account = mapAccount(rs);

        return new User(userId, phoneNumber, email, gender, avatar, fullName, address, account);
    }

    public static Book mapBook(ResultSet rs) throws SQLException {
        int id = rs.getInt("book.id");
        int bookItemId = rs.getInt("book.bookItemId");
        String isbn = rs.getString("book.ibsn");
        String title = rs.getString("book.tittle");
        String summary = rs.getString("book.sumary");
        String publicationYear = rs.getString("book.publicationYear");
        int numberOfPage = rs.getInt("book.numberOfPage");
        float cost = rs.getFloat("book.cost");
        String language = rs.getString("book.language");
        int remaningQuantity = rs.getInt("book.remainingQuantity");
        boolean status = rs.getBoolean("book.status");

        Book book = new Book(id, isbn, title, summary, publicationYear, numberOfPage, remaningQuantity, status, cost, language);
        book.setBookItemId(bookItemId);
        return book;
    }

    public static Author mapAuthor(ResultSet rs) throws SQLException {
        int id = rs.getInt("author.id");
        String name = rs.getString("author.name");
        String biography = rs.getString("author.biography");
        String email = rs.getString("author.email");
        String address = rs.getString("author.address");

        return new Author(id, name, biography, email, address);
    }

    public static Publisher mapPublisher(ResultSet rs) throws SQLException {
        int id = rs.getInt("publisher.id");
        String name = rs.getString("publisher.name");

        return new Publisher(id, name, null);
    }

    public static BookItem mapBookItem(ResultSet rs) throws SQLException {
        int id = rs.getInt("bookitem.id");
        String name = rs.getString("bookitem.name");
        String description = rs.getString("bookitem.description");
        float price = rs.getFloat("bookitem.price");
        float discount = rs.getFloat("bookitem.discount");
        int sellingStatus = rs.getInt("bookitem.sellingStatus");
        String image = rs.getString("bookitem.image");
        String category = rs.getString("bookitem.category");

        return new BookItem(id, price, discount, sellingStatus, description, image, name, category);
    }

    public static Order mapOrderInfoOnly(ResultSet rs) throws SQLException {
        Order order = new Order(
                rs.getInt("order.id"),
                rs.getInt("order.customerUserId"),
                rs.getInt("order.status"),
                rs.getDate("order.createdDate")
        );

        return order;
    }

    public static Order mapOrder(ResultSet rs) throws SQLException {
        Shipment shipmentInfo = mapShipment(rs);
        Payment paymentInfo = mapPayment(rs);

        Order order = new Order(
                rs.getInt("order.id"),
                rs.getInt("order.customerUserId"),
                rs.getDate("order.createdDate"),
                rs.getInt("order.status"),
                shipmentInfo,
                paymentInfo
        );

        return order;
    }

    public static Shipment mapShipment(ResultSet rs) throws SQLException {
        int id = rs.getInt("shipment.id");
        String type = rs.getString("shipment.type");
        float cost = rs.getFloat("shipment.cost");
        String shipUnit = rs.getString("shipment.shipunit");

        return new Shipment(id, type, cost, shipUnit);
    }

    public static Payment mapPayment(ResultSet rs) throws SQLException {
        Payment paymentInfo = new Payment(
                rs.getInt("payment.id"),
                rs.getFloat("payment.amount"),
                rs.getInt("payment.status"),
                rs.getString("payment.type")
        );

        return paymentInfo;
    }
}
