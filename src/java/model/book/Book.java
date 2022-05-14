/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.book;

import java.util.List;

/**
 *
 * @author DELL
 */
public class Book {

    private int id;
    private String IBSN;
    private String title;
    private String summary;
    private String publicationYear;
    private int numberOfPage;
    private int remainingQuantity;
    private boolean Status;
    private float cost;
    private String language;
    private Publisher pub;
    private List<Author> aut;

    private int bookItemId;

    public Book() {
    }

    public Book(int id, String IBSN, String title, String summary, String publicationYear, int numberOfPage, int remainingQuantity, float cost, String language) {
        this.id = id;
        this.IBSN = IBSN;
        this.title = title;
        this.summary = summary;
        this.publicationYear = publicationYear;
        this.numberOfPage = numberOfPage;
        this.remainingQuantity = remainingQuantity;
        this.cost = cost;
        this.language = language;
    }

    public Book(int id, String IBSN, String title, String summary, String publicationYear, int numberOfPage, int remainingQuantity, boolean Status, float cost, String language) {
        this.id = id;
        this.IBSN = IBSN;
        this.title = title;
        this.summary = summary;
        this.publicationYear = publicationYear;
        this.numberOfPage = numberOfPage;
        this.remainingQuantity = remainingQuantity;
        this.Status = Status;
        this.cost = cost;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Publisher getPub() {
        return pub;
    }

    public void setPub(Publisher pub) {
        this.pub = pub;
    }

    public List<Author> getAut() {
        return aut;
    }

    public void setAut(List<Author> aut) {
        this.aut = aut;
    }

    public String getIBSN() {
        return IBSN;
    }

    public void setIBSN(String IBSN) {
        this.IBSN = IBSN;
    }

    public String getLanguage() {
        return language;
    }

    public void setBookItemId(int bookItemId) {
        this.bookItemId = bookItemId;
    }

    public boolean isStatus() {
        return Status;
    }

    public int getBookItemId() {
        return bookItemId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
