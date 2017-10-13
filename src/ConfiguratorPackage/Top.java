package ConfiguratorPackage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Top{
	private String tname;
	private String descriptionEnglish1;
	private String descriptionSpanish1;
	private String descriptionFrench1;
	private double wpbsf;
	private double pg1;
	private double pg2;
	private double pg3;
	private double pg4;
	private double pg5;
	private String image1;
	private String image2;
	private double tcost;
	
	public Top() {
		
	}
	
	public Top(String tname,String de1, String ds1, String df1,double wpbsf, double p1, double p2, double p3, double p4, double p5,
				String image1, String image2) {
	 	settName(tname);
	 	setDescriptionEnglish1(de1);
	 	setDescriptionSpanish1(ds1);
	 	setDescriptionFrench1(df1);
	 	setPg1(pg1);
	 	setPg2(p2);
	 	setPg3(p3);
	 	setPg4(p4);
	 	setPg5(p5);
	 	setImage1(image1);
	 	setImage2(image2);
	 	
 	}
	
	
	public String getDescriptionEnglish1() {
		return descriptionEnglish1;
	}
	
	
	public String getDescriptionSpanish1() {
		return descriptionSpanish1;
	}
	
	
	public String getDescriptionFrench1() {
		return descriptionFrench1;
	}

	public double getWpbsf() {
		return wpbsf;
	}
	
	
	public BigDecimal getPg1() {
		return new BigDecimal(pg1);
	}
	
	
	public BigDecimal getPg2() {
		return new BigDecimal(pg2);
	}
	

	public BigDecimal getPg3() {
		return new BigDecimal(pg3);
	}
	

	public BigDecimal getPg4() {
		return new BigDecimal(pg4);
	}
	

	public BigDecimal getPg5() {
		return new BigDecimal(pg5);
	}
	

	public String getImage1() {
		return image1;
	}

	public String getImage2() {
		return image2;
	}
	

	public void setDescriptionEnglish1(String descriptionEnglish1) {
		this.descriptionEnglish1 = descriptionEnglish1;
	}
	

	public void setDescriptionSpanish1(String descriptionSpanish1) {
		this.descriptionSpanish1 = descriptionSpanish1;
	}
	

	public void setDescriptionFrench1(String descriptionFrench1) {
		this.descriptionFrench1 = descriptionFrench1;
	}
	
	public void setWpbsf(double wpbsf) {
		this.wpbsf = wpbsf;
	}
	

	public void setPg1(double pg1) {
		this.pg1 = pg1;
	}
	

	public void setPg2(double pg2) {
		this.pg2 = pg2;
	}
	 

	public void setPg3(double pg3) {
		this.pg3 = pg3;
	}
	

	public void setPg4(double pg4) {
		this.pg4 = pg4;
	}
	

	public void setPg5(double pg5) {
		this.pg5 = pg5;
	}
	

	public void setImage1(String image1) {
		this.image1 = image1;
	}
	

	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
 	public void settName(String tname){
 		if(tname.equals("NT")||tname.equals("SW")||tname.equals("Y")||tname.equals("X")||tname.equals("Z")||tname.equals("SQ") || tname.equals("DEFAULT")) {
 			this.tname= tname;
 		}
 		else {
	 			throw new IllegalArgumentException("Invalid name");
	 	}
 		for(char c : tname.toCharArray()) {
 			if(Character.isLowerCase(c)){
 				this.tname= tname.toUpperCase();
 			}
 				
 		}
 	}
 	
 	public String gettName() {
 		return this.tname;
 	}
 	
 	public void settCost(double tcost) {
 		if(tcost < 0) {
 			throw new IllegalArgumentException("Cost can not be negative");
 		}
 		else {	
 			this.tcost=tcost;
 		}
 	}

 	public double gettCost() {
 		return this.tcost;
 	}
 	
 	public void write(Connection conn) {
 		String SQL = "INSERT INTO top_colors("
				   + "ty,"
				   + "description_1_english," + "description_1_spanish," + "description_1_french,"
				   + "wpbsf,"
				   + "pricing_group_1," + "pricing_group_2," + "pricing_group_3," + "pricing_group_4," + "pricing_group_5,"
				   + "image_1," + "image_2) "
				   + "VALUES("
				   + "?,"
				   + "?,?,?,"
				   + "?,"
				   + "?,?,?,?,?,"
				   + "?,?)";
		try(PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
			pstmt.setString(1, gettName());
			pstmt.setString(2, getDescriptionEnglish1());
			pstmt.setString(3, getDescriptionSpanish1());
			pstmt.setString(4, getDescriptionFrench1());
			pstmt.setDouble(5, getWpbsf());
			pstmt.setBigDecimal(6,getPg1());
			pstmt.setBigDecimal(7, getPg2());
			pstmt.setBigDecimal(8, getPg3());
			pstmt.setBigDecimal(9, getPg4());
			pstmt.setBigDecimal(10, getPg5());
			pstmt.setString(11,getImage1());
			pstmt.setString(12, getImage2());
			
			if(pstmt.execute()) {
				System.out.println(gettName() + " wrote successfully"); 
			}
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
 	}
 	
	@Override
	public String toString() {
		return "Top [tname=" + tname + ", descriptionEnglish1=" + descriptionEnglish1 + ", descriptionSpanish1="
				+ descriptionSpanish1 + ", descriptionFrench1=" + descriptionFrench1 + ", wpbsf=" + wpbsf + ", pg1="
				+ pg1 + ", pg2=" + pg2 + ", pg3=" + pg3 + ", pg4=" + pg4 + ", pg5=" + pg5 + ", image1=" + image1
				+ ", image2=" + image2;
	}
 	
 	
}
