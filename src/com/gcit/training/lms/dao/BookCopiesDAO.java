package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class BookCopiesDAO extends AbstractDAO {
	
	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookCopies bc) throws SQLException {
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)",
				new Object[] { bc.getBookId().getBookId(),bc.getBranchId().getBranchId(),bc.getNoOfCopies()});
	}
 
	public void update(BookCopies bcNew, BookCopies bcOld) throws SQLException {
		save("update tbl_book_copies set bookId = ?, branchId = ?, noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] { bcNew.getBookId(), bcNew.getBranchId(), bcNew.getNoOfCopies(),bcOld.getBookId(),bcOld.getBranchId()});
	}

	public void delete(BookCopies bc) throws SQLException {
		save("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { bc.getBookId(),bc.getBranchId() });
	}

	public BookCopies readOne(int bookId, int branchId) throws SQLException {
		List<BookCopies> list = (List<BookCopies>) read(
				"select * from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { bookId, branchId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<BookCopies> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		return (List<BookCopies>) read("select * from tbl_book_copies", null);
	}


	@Override
	protected List<BookCopies> processResult(ResultSet rs) throws SQLException {
		List<BookCopies> aList = new ArrayList<BookCopies>();
		while (rs.next()) {
			BookCopies bc = new BookCopies();
			
			BookDAO b = new BookDAO(connection);
			bc.setBookId(b.readOne(rs.getInt("bookId")));
			
			LibraryBranchDAO a = new LibraryBranchDAO(connection);
			bc.setBranchId(a.readOne(rs.getInt("branchId")));

			aList.add(bc);
		}

		return aList;
	}
}

