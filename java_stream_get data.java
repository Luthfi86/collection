String query = "SELECT NS, Pemilik, tgl_lap, Jp FROM btt";

if (kata_cari != null) {
  query = "SELECT NS, Pemilik, tgl_lap, Jp FROM btt WHERE NS like '%" + kata_cari + "%' OR Pemilik like '%" + kata_cari + "%' OR p59 like '%" + kata_cari + "%' ORDER BY No ASC";
}

try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "username", "password");
     Statement stmt = con.createStatement();
     ResultSet rs = stmt.executeQuery(query)) {
   while (rs.next()) {
     String NS = rs.getString("NS");
     String Pemilik = rs.getString("Pemilik");
     Date tgl_lap = rs.getDate("tgl_lap");
     String Jp = rs.getString("Jp");
   }
} catch (SQLException e) {
   e.printStackTrace();
}
