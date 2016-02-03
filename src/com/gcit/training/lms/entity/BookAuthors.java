package com.gcit.training.lms.entity;

public class BookAuthors {
	private Book bookId;
	private Author authorId;
	
	public Book getBookId() {
		return bookId;
	}
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	public Author getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}
}
