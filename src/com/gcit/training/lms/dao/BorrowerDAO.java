package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class BorrowerDAO extends AbstractDAO {
	
	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Borrower a) throws SQLException {
		save("insert into tbl_Borrower (name,address,phone) values (?,?,?)",
				new Object[] { a.getName(),a.getAddress(),a.getPhone() });
	}

	public void update(Borrower a) throws SQLException {
		save("update tbl_Borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { a.getName(), a.getAddress(),a.getPhone(),a.getCardNo()});
	}

	public void delete(Borrower a) throws SQLException {
		save("delete from tbl_Borrower where cardNo = ?",
				new Object[] { a.getCardNo() });
	}

	public Borrower readOne(int cardNo) throws SQLException {
		List<Borrower> list = (List<Borrower>) read(
				"select * from tbl_Borrower where cardNo = ?",
				new Object[] { cardNo });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public List<Borrower> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		return (List<Borrower>) read("select * from tbl_borrower", null);
	}
		
	public List<Borrower> readByName(String searchString, int pageNo) throws SQLException {
		setPageNo(pageNo);
		String qString = "%" + searchString + "%";
		return (List<Borrower>) read(
				"select * from tbl_borrower where name like ?",
				new Object[] { qString });
	}

	@Override
	protected List<Borrower> processResult(ResultSet rs) throws SQLException {
		List<Borrower> aList = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));

			aList.add(a);
		}

		return aList;
	}
}
