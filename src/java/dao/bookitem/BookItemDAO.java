/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.bookitem;

import java.util.List;
import model.book.BookItem;

/**
 *
 * @author ADMIN
 */
public interface BookItemDAO {

    int deleteBookItem(int id);

    int updateBookItem(BookItem bookItem);

    List<BookItem> getMultipleBookItem(int[] bookItemIdArray);

    BookItem getBookItem(int id);

    List<BookItem> getNewItems(int limit, int from, String itemName);

    List<BookItem> getNewItemsByCategory(int limit, int from, String category, String itemName);

    String getItemCategory(int itemId);

}
