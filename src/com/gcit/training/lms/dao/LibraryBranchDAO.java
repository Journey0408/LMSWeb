package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;

public class LibraryBranchDAO extends AbstractDAO {

	
	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(LibraryBranch a) throws SQLException {
		save("insert into tbl_library_branch (branchName,branchAddress) values (?,?)",
				new Object[] { a.getBranchName(),a.getBranchAddress()});
	}

	public void update(LibraryBranch a) throws SQLException {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { a.getBranchName(), a.getBranchAddress(),a.getBranchId()});
	}

	public void delete(LibraryBranch a) throws SQLException {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { a.getBranchId() });
	}

	public LibraryBranch readOne(int branchId) throws SQLException {
		List<LibraryBranch> list = (List<LibraryBranch>) read(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<LibraryBranch> readAll(int pageNo, int pageSize) throws SQLException {
		setPageNo(pageNo);
		return (List<LibraryBranch>) read("select * from tbl_library_branch", null);
	}

	public List<LibraryBranch> readByName(String searchString,int pageNo) throws SQLException {
		setPageNo(pageNo);
		String qString = "%" + searchString + "%";
		return (List<LibraryBranch>) read(
				"select * from tbl_library_branch where branchName like ?",
				new Object[] { qString });
	}

	@Override
	protected List<LibraryBranch> processResult(ResultSet rs) throws SQLException {
		List<LibraryBranch> aList = new ArrayList<LibraryBranch>();
		while (rs.next()) {
			LibraryBranch a = new LibraryBranch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));

			aList.add(a);
		}

		return aList;
	}
}
