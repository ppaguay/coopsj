/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.fin.coopsanjose.riesgos.accesodatos;
import java.sql.*;
  
/**
 *
 * @author ULVIER
 */
public  class Conexion
{
    public  String driver;  
    public  String url;
    public  String user;
    public String pass;
    public Connection con;
    public PreparedStatement prStm;
    public ResultSet rs;

	

	public String getDriver(){	
		return this.driver;
	}

	public String getUrl(){	
		return this.url;
	}
	public String getUser(){
		return this.user;
	}

	public void setUser(String user){
		this.user=user;
	}

	public void setPassword(String pass){
		this.pass=pass;
	}

	public  Conexion() throws Exception{
    Global global = new Global();
        try {
            Class.forName(global.getDRIVER());
         con = DriverManager.getConnection(global.getURL(),global.getUSER(),global.getPASS());
        } catch (Exception e) {
            throw  e; 
        }
	} 

    public ResultSet ejecutaQuery(String sql) throws SQLException, ClassNotFoundException
    {
         rs = null;      
            try {
             Statement st = con.createStatement();           
             rs = st.executeQuery(sql);
         

        } catch (SQLException exConec) {
              throw  exConec;
            }
      
         return rs;
    }

     public int ejecutaQueryEscalar(String sql) throws Exception
      {
        int res=0;
       
            try {       
             Statement st = con.createStatement();
             res = st.executeUpdate(sql);
           
        } catch (SQLException exConec) {
                throw  exConec;
            }
      
       
         return res;
      }
         public boolean ejecutaPreparedComando(PreparedStatement prStm) throws Exception
        {
       int i=-1;
            try {
            i= prStm.executeUpdate();
        } catch (SQLException exConec) {
               throw  exConec;

        }
       if(i>0)
           return true;
       else
           return false;
        }

          public int ejecutaPreparedInt(PreparedStatement prStm) throws Exception
        {
       int i=-1;
            try {

             rs= prStm.executeQuery();
             while(rs.next())
            {
                i=rs.getInt(1);

             }
           

        } catch (SQLException exConec) {
               throw  exConec;

        }
        return i;
        }

       public ResultSet ejecutaPrepared(PreparedStatement prStm) throws Exception
        {
        rs=null;
            try {
              rs =prStm.executeQuery();

        } catch (SQLException exConec) {
                throw exConec;
        }
        return rs;
        }


         public PreparedStatement creaPreparedSmt(String sql) throws Exception
        {
       prStm=null;
    
       
            try {
              prStm = con.prepareStatement(sql);
          } catch (SQLException exConec) {
           throw  exConec;
            }
    
         return prStm;
        }

         public void desconectar () throws Exception
         {
             try
             {           
            con.close();
            con=null;
             }
             catch(Exception ex)
             {
                 throw ex;
             }
         }




}

