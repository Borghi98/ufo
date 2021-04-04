package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {
	
	public List<String> readShapes(){
		try {
			Connection conn = DBConnect.getConnection();
		    String sql = "SELECT DISTINCT shape "
				+ "FROM sighting "
				+ "WHERE shape<>'' "
				+ "ORDER BY shape ASC" ;
		
		    PreparedStatement st = conn.prepareStatement(sql);
		
		    ResultSet res = st.executeQuery();
		
		    List<String> formeUFO = new ArrayList<>();
		    while(res.next()) {
			    String forma = res.getString("shape");
			    formeUFO.add(forma);
		    }
			st.close();
			conn.close();
			return formeUFO;
		}catch (SQLException e) {
			throw new RuntimeException("Database error in readShapes",e);
			
		}
		
	}
	
	public int countByShape(String shape) {
		try {
		
			Connection conn = DBConnect.getConnection();
        	
			String sql = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? " ;
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, shape);
			ResultSet res = st.executeQuery();
			
			res.first();    // so gia che c'e solo un parametro (inutile while) 
			int count = res.getInt("cnt");
			st.close();
	        conn.close();
	        
			return count;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error in CountByShape",e);
		}
		
	}

}
