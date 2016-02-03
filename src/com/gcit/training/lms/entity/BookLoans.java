package com.gcit.training.lms.entity;


public class BookLoans {
	private Book bookId;
	private LibraryBranch branchId;
	private Borrower cardNo;
	private String dateOut;
	private String dueDate;
	private String dateIn;
	
	public Book getBookId() {
		return bookId;
	}
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	public LibraryBranch getBranchId() {
		return branchId;
	}
	public void setBranchId(LibraryBranch branchId) {
		this.branchId = branchId;
	}
	public Borrower getCardNo() {
		return cardNo;
	}
	public void setCardNo(Borrower cardNo) {
		this.cardNo = cardNo;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	
	public String getDateOut() {
		return dateOut;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDateIn() {
		return dateIn;
	}
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	
}
