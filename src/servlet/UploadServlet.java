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
import javax.servlet.http.Part;

import dto.ConFile;



@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize=1048576)  // 最大1M
public class UploadServlet extends HttpServlet {
  final File uploadDir = new File("C:\\pleiades\\workspace\\BulletinBoard\\WebContent\\upload");  // ファイル保存先
  public void init() throws ServletException {
    uploadDir.mkdir();
  }
  public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
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
    // HTML を返す ->
    res.setContentType("text/html; charset=utf-8");
    res.getWriter().write(
      "<html><body>"
      + "<form action = '/BulletinBoard/Bull' method='post'>"
      + "<input type='submit' value='トップへ戻る'>"
      + "</form>"


    );
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

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		String view = "/WEB-INF/view/bull.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);

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