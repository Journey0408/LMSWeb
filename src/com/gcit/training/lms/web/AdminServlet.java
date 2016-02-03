package com.gcit.training.lms.web;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookCopies;
import com.gcit.training.lms.entity.BookLoans;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.LibraryBranch;
import com.gcit.training.lms.entity.Publisher;
import com.gcit.training.lms.service.AdministrativeService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/deleteAuthor", "/pageAuthor", "/searchAuthor",
		"/addBook", "/deleteBook", "/addLibraryBranch", "/addGenre",
		"/addBorrower","/addBookCopies","/addBookLoans" })
//TODO
public class AdminServlet extends HttpServlet {
	AdministrativeService adminService = new AdministrativeService();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/deleteAuthor":
			try {
				deleteAuthor(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/pageAuthor":
			try {
				readAllAuthors(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/searchAuthor":
			try {
				readAllAuthors(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void readAllAuthors(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewauthor.jsp");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String searchString = "";
		if (request.getParameter("searchString") != null)
			searchString = request.getParameter("searchString");

		List<Author> authors = adminService.getAllAuthors(pageNo, 10,
				searchString);
		request.setAttribute("authors", authors);
		StringBuilder str = new StringBuilder();
		str.append("<thead><tr><th>#</th><th>Author Name</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
		for (Author author : authors) {
			str.append("<tr><td>"
					+ author.getAuthorId()
					+ "</td><td>"
					+ author.getAuthorName()
					+ "</td><td><button type='button' class='btn btn-sm btn-primary'>Edit Author</button><td><button type='button' class='btn btn-sm btn-danger' onclick='javascript:location.href='deleteAuthor?authorId="
					+ author.getAuthorId() + "';'>Delete Author</button></tr>");
		}
		response.getWriter().append(str.toString());

	}

	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewauthor.jsp");
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		try {
			adminService.deleteAuthor(authorId);
			request.setAttribute("result", "Author Deleted Sucessfully.");
			rd.forward(request, response);
		} catch (ServletException e) {
			request.setAttribute("result", "Author Delete Failed.");
			e.printStackTrace();
			rd.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//TODO
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/addAuthor":
			addAuthor(request, response);
			break;
		case "/searchAuthor":
			try {
				readAllAuthors(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/addLibraryBranch":
			addLibraryBranch(request, response);
			break;
		case "/addBook":
			try {
				addBook(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/addGenre":
			addGenre(request, response);
			break;
		case "/addBorrower":
			addBorrower(request, response);
			break;
		case "/addBookCopies":
			addBookCopies(request, response);
			break;
		case "/addBookLoans":
			addBookLoans(request, response);
			break;
		default:
			break;
		}

	}

	private void addBookLoans(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		String dateIn = request.getParameter("dateIn");
		String dateOut = request.getParameter("dateOut");
		String dueDate = request.getParameter("dueDate");
		BookLoans bl = new BookLoans();
		
		Book bk = new Book();
		bk.setBookId(bookId);
		bl.setBookId(bk);
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchId(branchId);		
		bl.setBranchId(lb);
		Borrower br = new Borrower();
		br.setCardNo(cardNo);
		bl.setCardNo(br);
		bl.setDateIn(dateIn);
		bl.setDateOut(dateOut);
		bl.setDueDate(dueDate);
		try {
			adminService.addBookLoans(bl);
			request.setAttribute("result", "Book Loans Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addBookLoans.jsp");
			request.setAttribute("result", "BookLoans Add Failed!");
			rd.forward(request, response);
		}
	}

	private void addBookCopies(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int copies = Integer.parseInt(request.getParameter("copies"));
		
		BookCopies bc = new BookCopies();
		Book bk = new Book();
		bk.setBookId(bookId);
		bc.setBookId(bk);
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchId(branchId);
		bc.setBranchId(lb);
		bc.setNoOfCopies(copies);
		try {
			adminService.addBookCopies(bc);
			request.setAttribute("result", "Book Copies Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addBookCopies.jsp");
			request.setAttribute("result", "BookCopies Add Failed!");
			rd.forward(request, response);
		}
	}

	private void addBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		String borrowerName = request.getParameter("borrowerName");
		if (borrowerName.length() < 1 || borrowerName.length() > 40) {
			rd = getServletContext().getRequestDispatcher("/addBorrower.jsp");
			request.setAttribute("result",
					"Borrower Name cannot be more than 45 chars!");
			rd.forward(request, response);
		}
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		Borrower borrower = new Borrower();
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borrower.setPhone(borrowerPhone);
		try {
			adminService.addBorrower(borrower);
			request.setAttribute("result", "Borrower Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addBorrower.jsp");
			request.setAttribute("result", "Borrower Add Failed!");
			rd.forward(request, response);
		}
	}

	private void addGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		String genreName = request.getParameter("genreName");
		if (genreName.length() < 1 || genreName.length() > 40) {
			rd = getServletContext().getRequestDispatcher("/addGenre.jsp");
			request.setAttribute("result",
					"Genre Name cannot be more than 45 chars!");
			rd.forward(request, response);
		}
		Genre genre = new Genre();
		genre.setGenre_name(genreName);
		try {
			adminService.addGenre(genre);
			request.setAttribute("result", "Genre Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addGenre.jsp");
			request.setAttribute("result", "Genre Add Failed!");
			rd.forward(request, response);
		}
	}

	private void addLibraryBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		String branchName = request.getParameter("branchName");
		if (branchName.length() < 1 || branchName.length() > 40) {
			rd = getServletContext().getRequestDispatcher(
					"/addLibraryBranch.jsp");
			request.setAttribute("result",
					"Branch Name cannot be more than 45 chars!");
			rd.forward(request, response);
		}
		String branchAddress = request.getParameter("branchAddress");

		LibraryBranch branch = new LibraryBranch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
		adminService.addLibraryBranch(branch);
		request.setAttribute("result", "LibraryBranch Added Sucessfully!");
		rd.forward(request, response);

	}

	private void addAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		String authorName = request.getParameter("authorName");
		if (authorName.length() < 1 || authorName.length() > 40) {
			rd = getServletContext().getRequestDispatcher("/addauthor.jsp");
			request.setAttribute("result",
					"Author Name cannot be more than 45 chars!");
			rd.forward(request, response);
		}
		Author author = new Author();
		author.setAuthorName(authorName);
		try {
			adminService.addAuthor(author);
			request.setAttribute("result", "Author Added Sucessfully!");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			rd = getServletContext().getRequestDispatcher("/addauthor.jsp");
			request.setAttribute("result", "Author Add Failed!");
			rd.forward(request, response);
		}
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		String title = request.getParameter("title");
		System.out.println(request.getParameter("title"));
		
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		System.out.println(request.getParameter("publisherId"));
		Book book = new Book();
		book.setTitle(title);
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		book.setPublisher(publisher);
		adminService.addBook(book);
		request.setAttribute("result", "Book Added Sucessfully!");
		rd.forward(request, response);
	}

}
