
package DashDataBase;
 
import java.io.*;
import java.sql.*;

public class AccesoBD{

    public static String [][] CONSUMO_TOTAL() {
       
      String obj[][]=null;
      Connection con = null;
      Statement st = null;
      ResultSet rs = null;
      
      try {
          
          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          con = DriverManager.getConnection("jdbc:odbc:PRY_ODBC");
          st = con.createStatement();
          rs = st.executeQuery("SELECT CAST (SUBSTRING(FECHA,7,4)+SUBSTRING(FECHA,4,2)+SUBSTRING(FECHA,1,2) AS SMALLDATETIME) FECHA,SUM(CAST (REPLACE(RECARGA,',','.') AS FLOAT)) MONTO FROM RECARGA WHERE CAST (SUBSTRING(FECHA,7,4)+SUBSTRING(FECHA,4,2)+SUBSTRING(FECHA,1,2) AS SMALLDATETIME)>='20120501' AND CAST (SUBSTRING(FECHA,7,4)+SUBSTRING(FECHA,4,2)+SUBSTRING(FECHA,1,2) AS SMALLDATETIME)<='20120531' GROUP BY FECHA ORDER BY FECHA");

          ResultSetMetaData rsmd = rs.getMetaData();
          int numCols = rsmd.getColumnCount();
          int numFils =31;
          int j = 0;
          obj=new String[numFils][numCols];
          
         while (rs.next()) {
            for (int i=0;i<numCols;i++)
            {
                //System.out.println(i+" "+j+" "+numCols+" "+numFils);
                obj[j][i]=rs.getString(i+1);
            }
            j++;
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         if (rs != null) try { rs.close(); } catch(Exception e) {System.out.println(e.toString());}
         if (st != null) try { st.close(); } catch(Exception e) {System.out.println(e.toString());}
         if (con != null) try { con.close(); } catch(Exception e) {System.out.println(e.toString());}
      }
     return obj;
   }
}