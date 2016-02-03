package com.gcit.training.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.BookCopiesDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.BookLoansDAO;
import com.gcit.training.lms.dao.BorrowerDAO;
import com.gcit.training.lms.dao.GenreDAO;
import com.gcit.training.lms.dao.LibraryBranchDAO;
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.BookLoans;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.LibraryBranch;
import com.mysql.jdbc.StringUtils;

public class AdministrativeService {
	
	public void addAuthor(Author author) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			adao.create(author);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
		
	}
	
	public void addBook(Book book) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookDAO bdao = new BookDAO(connection);
			bdao.create(book);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
		
	}
	
	public List<Author> getAllAuthors(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return adao.readAll(pageNo, pageSize);
			}else{
				return adao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}
	
	
	public List<LibraryBranch> getAllLibraryBranch(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			LibraryBranchDAO lbdao = new LibraryBranchDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return lbdao.readAll(pageNo, pageSize);
			}else{
				return lbdao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}
	
	public List<Book> getAllBook(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookDAO bdao = new BookDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return bdao.readAll(pageNo, pageSize);
			}else{
				return bdao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}
	
	public List<Borrower> getAllBorrowers(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return bdao.readAll(pageNo, pageSize);
			}else{
				return bdao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}
	
	public List<Genre> getAllGenres(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			GenreDAO gdao = new GenreDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return gdao.readAll(pageNo, pageSize);
			}else{
				return gdao.readByName(searchString, pageNo);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}
	
	
	public List<BookCopies> getAllBookCopies(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookCopiesDAO bcdao = new BookCopiesDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return bcdao.readAll(pageNo, pageSize);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
		return null;
	}
	
	public List<BookLoans> getAllBookLoans(int pageNo, int pageSize, String searchString) throws SQLException{
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookLoansDAO bcdao = new BookLoansDAO(connection);
			if(StringUtils.isNullOrEmpty(searchString)){
				return bcdao.readAll(pageNo, pageSize);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
		return null;
	}
	
	public void deleteAuthor(Integer authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			adao.delete(author);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

	public List<Author> searchAuthors(String searchString, Integer pageNo) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(connection);
			return adao.readByName(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			connection.close();
		}
	}

	public void addLibraryBranch(LibraryBranch branch) {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			LibraryBranchDAO lbdao = new LibraryBranchDAO(connection);
			lbdao.create(branch);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addGenre(Genre genre) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			GenreDAO gdao = new GenreDAO(connection);
			gdao.create(genre);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
	}

	public void addBorrower(Borrower borrower) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(connection);
			bdao.create(borrower);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
	}

	public void addBookCopies(BookCopies bc) throws SQLException {
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookCopiesDAO bcdao = new BookCopiesDAO(connection);
			bcdao.create(bc);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
	}

	public void addBookLoans(BookLoans bl) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionUtil connUtil = new ConnectionUtil();
		Connection connection = connUtil.getConnection();
		try{
			BookLoansDAO bcdao = new BookLoansDAO(connection);
			bcdao.create(bl);
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
			connection.rollback();
		}finally{
			connection.close();
		}
	}

}
