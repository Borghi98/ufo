package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
		
        String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root" ;
        
        try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			/* Statement st = conn.createStatement();
			
			String sql = "SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC" ;
			
			ResultSet res = st.executeQuery(sql);
			
			List<String> formeUFO = new ArrayList<>();
			while(res.next()) {
				String forma = res.getString("shape");
				formeUFO.add(forma);
			}
			*/
			
			String sql = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? " ;
			String shapeScelta ="circle";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, shapeScelta);
			ResultSet res = st.executeQuery();
			res.first();    // so gia che c'e solo un parametro (inutile while) 
			int count = res.getInt("cnt");
			st.close();
	        
			System.out.println("UFO di forma "+ shapeScelta+" sono: "+ count);
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
