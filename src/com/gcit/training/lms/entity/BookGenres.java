package com.gcit.training.lms.entity;

public class BookGenres {
	private Genre genre_id;
	private Book bookId;
	public Genre getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(Genre genre_id) {
		this.genre_id = genre_id;
	}
	public Book getBookId() {
		return bookId;
	}
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	
}
