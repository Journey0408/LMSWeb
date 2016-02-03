package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;

public class BookDAO extends AbstractDAO {

	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Book b) throws SQLException {
		save("insert into tbl_book (title,pubId) values (?,?)",
				new Object[] { b.getTitle(), b.getPublisher().getPublisherId()});
		
//		List<Book> books = (List<Book>) read("select * from tbl_book where title = ? and pubId = ?",
//				new Object[]{ b.getTitle(), b.getPublisher().getPublisherId()});
//		int id = books.get(0).getBookId();
//		
//		//insert the bookId and Author into book_author;
//		List<Author> authorList = b.getAuthors();
//		for (Author author : authorList) {
//			save("insert into tbl_book_authors (bookId, authorId) values(?,?) where bookId = ?", 
//					new Object[]{id, author.getAuthorId(),id});
//		}
	}

	public void update(Book bNew, Book bOld) throws SQLException {
		save("update tbl_book set title = ?, publisher = ? where bookId = ?",
				new Object[] { bNew.getTitle(), bNew.getPublisher().getPublisherId(),
						bNew.getBookId() });
		
		int newAuthorID = bNew.getAuthors().get(0).getAuthorId();
		int oldAuthorID = bOld.getAuthors().get(0).getAuthorId();
		
		save("update tbl_book_authros set authorId = ? where bookId = ? and authorId = ?",
				new Object[] { newAuthorID, bOld.getBookId(),
				oldAuthorID});
	}

	public void delete(Book b) throws SQLException {
		System.out.println("dddd");
		save("delete from tbl_book where bookId = ?",
				new Object[] { b.getBookId() });
	}

	public Book readOne(int bookId) throws SQLException {
		List<Book> bookList = (List<Book>) read(
				"select * from tbl_book where bookId = ?",
				new Object[] { bookId });
		
		if(bookList != null && bookList.size() > 0) {
			return bookList.get(0);
		} else {
			return null;
		}
	}

	public List<Book> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		return (List<Book>) read("select * from tbl_book", null);
	}

	public List<Book> readByName(String searchString, int pageNo) throws SQLException {
		setPageNo(pageNo);
		String qString = "%" + searchString + "%";
		return (List<Book>) read(
				"select * from tbl_book where bookName like ?",
				new Object[] { qString });
	}

	@Override
	protected List<Book> processResult(ResultSet rs) throws SQLException {
		List<Book> aList = new ArrayList<Book>();
		while (rs.next()) {
			Book a = new Book();
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
			
			PublisherDAO p = new PublisherDAO(connection);
			a.setPublisher(p.readOne(rs.getInt("pubId")));
   
			aList.add(a);
		}

		return aList;
	}

}
