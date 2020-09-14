package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dto.ConFile;
import dto.Contents;


/**
 * Servlet implementation class List
 */
@WebServlet("/Bull")
@MultipartConfig(maxFileSize=1048576)  // 最大1M
public class Bull extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bull() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String human = request.getParameter("human");
		String content = request.getParameter("content");
		String mail = request.getParameter("mail");
		HttpSession session = request.getSession();
		String time = (String)session.getAttribute("time");
		if(time == null){
			time = "";
		}else if(human == "" || human == null){
			System.out.println("入力されていない項目があります");
		}else if(content == "" || content == null ){
			System.out.println("入力されていない項目があります");
		}else if(mail == "" || mail == null){
			System.out.println("入力されていない項目があります");
		}else{
			Contents ins = new Contents(content,human,time,mail,"編集されていません" , "a");
			dao.BullDao.insertCon(ins);
			content = null;
			System.out.println("正常に実行されました。");
		}

		String view = "/WEB-INF/view/bull.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	final File uploadDir = new File("C:\\pleiades\\workspace\\BulletinBoard\\WebContent\\upload");  // ファイル保存先
	public void init() throws ServletException {
		uploadDir.mkdir();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{

		req.setCharacterEncoding("UTF-8");
		// ファイルの保存 ->
		String id = req.getParameter("id");
		Part fPart = req.getPart("file");
		String fName = (new StringBuilder(id)
				.append("_").append(System.currentTimeMillis())
				.append("_").append(fPart.getSubmittedFileName()
						).toString());
		save(fPart, new File(uploadDir, fName));
		String p = getFileName(fPart);
		ConFile p2 = new ConFile(p);
		dao.FileDao.insertFile(p2);
		System.out.println(p);

		String human = req.getParameter("human");
		String content = req.getParameter("content");
		String mail = req.getParameter("mail");
		HttpSession session = req.getSession();
		String time = (String)session.getAttribute("time");
		if(time == null){
			time = "";
		}else if(human == "" || human == null){
			System.out.println("入力されていない項目があります");
		}else if(content == "" || content == null ){
			System.out.println("入力されていない項目があります");
		}else if(mail == "" || mail == null){
			System.out.println("入力されていない項目があります");
		}else{
			System.out.println(p);
			Contents ins = new Contents(content,human,time,mail,"編集されていません", p);
			dao.BullDao.insertCon(ins);
			content = null;
			System.out.println("正常に実行されました。");
		}

		String view = "/WEB-INF/view/bull.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, res);

	}
	public void save(Part in, File out) throws IOException {
		BufferedInputStream br
		= new BufferedInputStream(in.getInputStream());
		try (BufferedOutputStream bw =
				new BufferedOutputStream(new FileOutputStream(out))
				) {
			int len = 0;
			byte[] buff = new byte[1024];
			while ((len = br.read(buff)) != -1) {
				bw.write(buff, 0, len);
			}
		}

	}

	public String getFileName(Part part) {
	      String name = null;
	      for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
	          if (dispotion.trim().startsWith("filename")) {
	              name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
	              name = name.substring(name.lastIndexOf("\\") + 1);
	              break;
	          }
	      }
	      return name;
	  }
}
