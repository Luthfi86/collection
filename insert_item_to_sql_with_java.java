import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/InsertData")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
                 maxFileSize=1024*1024*10,
                 location="lap/logo")
public class InsertData extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private final String DB_URL = "jdbc:mysql://localhost:3306/db_name";
   private final String DB_USER = "username";
   private final String DB_PASS = "password";
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String NS = request.getParameter("NS");
       String Pemilik = request.getParameter("Pemilik");
       String Alamat = request.getParameter("Alamat");
       Part img1 = request.getPart("img1");
       String Jp = request.getParameter("Jp");
       String Jp2 = request.getParameter("Jp2");
       String pabrik = request.getParameter("pabrik");
       String tahun_buat = request.getParameter("tahun_buat");
       String merk = request.getParameter("merk");
       String pabrik2 = request.getParameter("pabrik2");
       String tipe = request.getParameter("tipe");
       Part img3 = request.getPart("img3");

       Random rand = new Random();
       int angka_acak = rand.nextInt(999) + 1;
       String logobaru = angka_acak + "-" + img1.getSubmittedFileName();
       String gambaralat = angka_acak + "-" + img3.getSubmittedFileName();

       Connection conn = null;
       PreparedStatement pstmt = null;
       
       try {
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
           
        String sql = "INSERT INTO btt (NS,Pemilik, Alamat, logo,Jp,Jp2,pabrik,tahun_buat,merk,pabrik2,tipe,gambaralat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, NS);
        psmt.setString(2,Pemilik)
        pstmt.setString(3, Alamat);
        pstmt.setString(4, logobaru);
        pstmt.setString(5, Jp);
        pstmt.setString(6, Jp2);
        pstmt.setString(7, pabrik);
        pstmt.setString(8, tahun_buat);
        pstmt.setString(9, merk);
        pstmt.setString(10, pabrik2);
        pstmt.setString(11, tipe);
        pstmt.setString(12, gambaralat);
        pstmt.executeUpdate();
       img1.write(logobaru);
       img3.write(gambaralat);

       response.sendRedirect("Data.jsp");

   } catch (SQLException | ClassNotFoundException e) {
       System.err.println("Error: " + e.getMessage());
   } finally {
       try {
           if (conn != null) {
               conn.close();
           }
           if (pstmt != null) {
               pstmt.close();
           }
       } catch (SQLException e) {
           System.err.println("Error: " + e.getMessage());
       }
   }
