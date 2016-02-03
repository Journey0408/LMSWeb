package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class BookGenresDAO extends AbstractDAO {

	public void create(BookGenres bg) throws SQLException {
		save("insert into tbl_book_genres (bookId,genre_id) values (?,?)",
				new Object[] { bg.getBookId(),bg.getGenre_id() });
	}

	public void update(BookGenres bgNew, BookGenres bgOld) throws SQLException {
		save("update tbl_book_genres set bookId = ?, genre_id = ? where bookId = ? and genre_id = ?",
				new Object[] { bgNew.getBookId(), bgNew.getGenre_id(), bgOld.getBookId(),bgOld.getGenre_id()});
	}

	public void delete(BookGenres bg) throws SQLException {
		save("delete from tbl_book_genres where bookId = ? and genre_id = ?",
				new Object[] { bg.getBookId(),bg.getGenre_id() });
	}

	public BookGenres readOne(int bookId, int genre_id) throws SQLException {
		List<BookGenres> list = (List<BookGenres>) read(
				"select * from tbl_book_genres where bookId = ? and genre_id = ?",
				new Object[] { bookId, genre_id });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<BookGenres> readAll() throws SQLException {
		return (List<BookGenres>) read("select * from tbl_book_genres", null);
	}


	@Override
	protected List<BookGenres> processResult(ResultSet rs) throws SQLException {
		List<BookGenres> aList = new ArrayList<BookGenres>();
		while (rs.next()) {
			BookGenres bg = new BookGenres();
			
			BookDAO b = new BookDAO();
			bg.setBookId(b.readOne(rs.getInt("bookId")));
			
			GenreDAO a = new GenreDAO();
			bg.setGenre_id(a.readOne(rs.getInt("genre_id")));

			aList.add(bg);
		}

		return aList;
	}
}

