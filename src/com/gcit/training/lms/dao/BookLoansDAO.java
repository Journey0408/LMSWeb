package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;


public class BookLoansDAO extends AbstractDAO {
	
	public BookLoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookLoans bl) throws SQLException {
		save("insert into tbl_book_loans (bookId,branchId,CardNo,dateOut,dueDate,dateIn) values (?,?,?,?,?,?)",
				new Object[] {bl.getBookId().getBookId(),bl.getBranchId().getBranchId(),bl.getCardNo().getCardNo(),bl.getDateOut(),bl.getDueDate(),bl.getDateIn()});
	}

	public void update(BookLoans blNew, BookLoans blOld) throws SQLException {
		save("update tbl_book_loans set bookId = ?, branchId = ?, cardNo = ?, dateOut = ?,dueDate = ?,dateIn = ? where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { blNew.getBookId(), blNew.getBranchId(), blNew.getCardNo(),blNew.getDateOut(),blNew.getDueDate(),blNew.getDateIn(),blOld.getBookId(),blOld.getBranchId(),blOld.getCardNo()});
	}

	public void delete(BookLoans bl) throws SQLException {
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bl.getBookId(),bl.getBranchId(),bl.getCardNo() });
	}

	public BookLoans readOne(int bookId, int branchId, int cardNo) throws SQLException {
		List<BookLoans> list = (List<BookLoans>) read(
				"select * from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bookId, branchId, cardNo });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<BookLoans> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		return (List<BookLoans>) read("select * from tbl_book_loans", null);
	}


	@Override
	protected List<BookLoans> processResult(ResultSet rs) throws SQLException {
		List<BookLoans> aList = new ArrayList<BookLoans>();
		while (rs.next()) {
			BookLoans bl = new BookLoans();
			
			BookDAO b = new BookDAO(connection);
			bl.setBookId(b.readOne(rs.getInt("bookId")));
			
			LibraryBranchDAO a = new LibraryBranchDAO(connection);
			bl.setBranchId(a.readOne(rs.getInt("branchId")));
			
			BorrowerDAO c = new BorrowerDAO(connection);
			bl.setCardNo(c.readOne(rs.getInt("cardNo")));
			
			bl.setDateOut(rs.getString("dateOut"));
			bl.setDueDate(rs.getString("DueDate"));
			bl.setDateIn(rs.getString("dateIn"));
			
			aList.add(bl);
		}

		return aList;
	}
}

