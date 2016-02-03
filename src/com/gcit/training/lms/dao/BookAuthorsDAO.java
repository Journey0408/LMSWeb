package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class BookAuthorsDAO extends AbstractDAO {

	public void create(BookAuthors ba) throws SQLException {
		save("insert into tbl_book_authors (bookId,authorId) values (?,?)",
				new Object[] { ba.getBookId(),ba.getAuthorId() });
	}

	public void update(BookAuthors baNew, BookAuthors baOld) throws SQLException {
		save("update tbl_book_authors set bookId = ?, authorId = ? where bookId = ? and authorId = ?",
				new Object[] { baNew.getBookId(), baNew.getAuthorId(), baOld.getBookId(),baOld.getAuthorId()});
	}

	public void delete(BookAuthors ba) throws SQLException {
		save("delete from tbl_book_authors where bookId = ? and authorId = ?",
				new Object[] { ba.getBookId(),ba.getAuthorId() });
	}

	public BookAuthors readOne(int bookId, int authorId) throws SQLException {
		List<BookAuthors> list = (List<BookAuthors>) read(
				"select * from tbl_book_authors where bookId = ? and authorId = ?",
				new Object[] { bookId, authorId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<BookAuthors> readAll() throws SQLException {
		return (List<BookAuthors>) read("select * from tbl_book_authors", null);
	}


	@Override
	protected List<BookAuthors> processResult(ResultSet rs) throws SQLException {
		List<BookAuthors> aList = new ArrayList<BookAuthors>();
		while (rs.next()) {
			BookAuthors ba = new BookAuthors();
			
			BookDAO b = new BookDAO();
			ba.setBookId(b.readOne(rs.getInt("bookId")));
			
			AuthorDAO a = new AuthorDAO();
			ba.setAuthorId(a.readOne(rs.getInt("authorId")));

			aList.add(ba);
		}

		return aList;
	}
}

