package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class GenreDAO extends AbstractDAO {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Genre a) throws SQLException {
		save("insert into tbl_genre (genre_Name) values (?)",
				new Object[] { a.getGenre_name() });
	}

	public void update(Genre a) throws SQLException {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { a.getGenre_name(), a.getGenre_id() });
	}

	public void delete(Genre a) throws SQLException {
		save("delete from tbl_genre where genre_id = ?",
				new Object[] { a.getGenre_id() });
	}

	public Genre readOne(int genre_id) throws SQLException {
		List<Genre> list = (List<Genre>) read(
				"select * from tbl_genre where genre_d = ?",
				new Object[] { genre_id });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public List<Genre> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		return (List<Genre>) read("select * from tbl_genre", null);
	}

	public List<Genre> readByName(String searchString, int pageNo) throws SQLException {
		setPageNo(pageNo);
		String qString = "%" + searchString + "%";
		return (List<Genre>) read(
				"select * from tbl_genre where genre_name like ?",
				new Object[] { qString });
	}

	@Override
	protected List<Genre> processResult(ResultSet rs) throws SQLException {
		List<Genre> aList = new ArrayList<Genre>();
		while (rs.next()) {
			Genre a = new Genre();
			a.setGenre_id(rs.getInt("genre_id"));
			a.setGenre_name(rs.getString("genre_name"));

			aList.add(a);
		}

		return aList;
	}
}
