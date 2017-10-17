package ConfiguratorPackage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Color{
	private String descriptionEnglish;
	private String descriptionSpanish;
	private String descriptionFrench;
	private String complimentary1;
	private String complimentary2;
	private String complimentary3;
	private String imageSolid125;
	private String imageSolid145;
	private String imageSolid300;
	private int cnumber;
	private double pg1;
	private double pg2;
	private double pg3;
	private double pg4;
	private double pg5;
	private double ccost;
	
	public Color() {
		this(0,"ENGLISH","SPANISH","FRENCH","COMIPLMENTARY1","COMIPLMENTARY2","COMIPLMENTARY3",0.0,0.0,0.0,0.0,0.0,"IMAGE125","IMAGE145","IMAGE300");
	}
	
	public Color(int cnumber,String english, String spanish, String french, String compilmentary1, String compilmentary2, String compilmentary3, double pg1,double pg2, double pg3, double pg4, double pg5,String image125,String image145, String image300) {
	 	setcNumber(cnumber);
	 	setDescriptionEnglish(english);
	 	setDescriptionSpanish(spanish);
	 	setDescriptionFrench(french);
	 	setComplimentary1(compilmentary1);
	 	setComplimentary2(compilmentary2);
	 	setComplimentary3(compilmentary3);
	 	setPg1(pg1);
	 	setPg2(pg2);
	 	setPg3(pg3);
	 	setPg4(pg4);
	 	setPg5(pg5);
	 	setImage125(image125);
	 	setImage145(image145);
	 	setImage300(image300);
	 	
 	}
 
	public void setcNumber(int cnumber){
 		this.cnumber= cnumber;
 	}
 	
 	public int getcNumber() {
 		return this.cnumber;
 	}
 	
 	public void setcCost(double ccost) {
 		if(ccost < 0) {
 			throw new IllegalArgumentException("Cost can not be negative");
 		}
 		else {	
 			this.ccost=ccost;
 		}
 	}

 	public double getcCost() {
 		return this.ccost;
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
 	public void setDescriptionEnglish(String english) {
 		this.descriptionEnglish = english;
 	}
 	
 	public void setDescriptionSpanish(String spanish) {
 		this.descriptionSpanish = spanish;
 	}
 	
 	public void setDescriptionFrench(String french) {
 		this.descriptionFrench = french;
 	}
 	
 	public void setComplimentary1(String compilmentary1) {
 		this.complimentary1 = compilmentary1;
 	}
 	
 	public void setComplimentary2(String compilmentary2) {
 		this.complimentary2 = compilmentary2;
 	}
 	
 	public void setComplimentary3(String compilmentary3) {
 		this.complimentary3 = compilmentary3;
 	}
 	
 	public void setImage125(String image125) {
 		this.imageSolid125 = image125;
 	}
 	
 	public void setImage145(String image145) {
 		this.imageSolid145 = image145;
 	}
 	
 	public void setImage300(String image300) {
 		this.imageSolid300 = image300;
 	}
 	
 	public String getDescriptionEnglish() {
 		return descriptionEnglish;
 	}
 	
 	public String getDescriptionSpanish() {
 		return descriptionSpanish;
 	}
 	
 	public String getDescriptionFrench() {
 		return descriptionFrench;
 	}
 	
 	public String getComplimentary1() {
 		return complimentary1;
 	}
 	
 	public String getComplimentary2() {
 		return complimentary2;
 	}
 	
 	public String getComplimentary3() {
 		return complimentary3;
 	}
 	
 	public String getImage125() {
 		return imageSolid125;
 	}
 	
	public String getImage145() {
 		return imageSolid145;
 	}
	
	public String getImage300() {
 		return imageSolid300;
 	}
	
	public BigDecimal getPg1() {
		BigDecimal copyPg1 = new BigDecimal(pg1);
		return copyPg1;
	}

	public BigDecimal getPg2() {
		BigDecimal copyPg2 = new BigDecimal(pg2);
		return copyPg2;
	}

	public BigDecimal getPg3() {
		BigDecimal copyPg3 = new BigDecimal(pg3);
		return copyPg3;
	}

	public BigDecimal getPg4() {
		BigDecimal copyPg4 = new BigDecimal(pg4);
		return copyPg4;
	}

	public BigDecimal getPg5() {
		BigDecimal copyPg5 = new BigDecimal(pg5);
		return copyPg5;
	}
	
	public void write(Connection conn) {
		String SQL ="INSERT INTO finish_colors("
				+ "finish_color_sku,"
				+ "description_1_english,"
				+ "description_1_spanish,description_1_french,"
				+ "complimentary_1,complimentary_2,complimentary_3,"
				+ "pricing_group_1,pricing_group_2,pricing_group_3,pricing_group_4,pricing_group_5,"
				+ "image_1,image_2) "
				+ "VALUES("
				+ "?,"
				+ "?,"
				+ "?,?,"
				+ "?,?,?,"
				+ "?,?,?,?,?,"
				+ "?,?)";
		
		try(PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS) ){
			pstmt.setInt(1, getcNumber());
			pstmt.setString(2, getDescriptionEnglish());
			pstmt.setString(3, getDescriptionSpanish());
			pstmt.setString(4, getDescriptionFrench());
			pstmt.setString(5, getComplimentary1());
			pstmt.setString(6, getComplimentary2());
			pstmt.setString(7, getComplimentary3());
			pstmt.setBigDecimal(8, getPg1());
			pstmt.setBigDecimal(9, getPg2());
			pstmt.setBigDecimal(10, getPg3());
			pstmt.setBigDecimal(11, getPg4());
			pstmt.setBigDecimal(12, getPg5());
			pstmt.setString(13, getImage125());
			pstmt.setString(14, getImage145());
			//pstmt.setString(15, color.getImage300());
			if(pstmt.execute()) {
				System.out.println(getcNumber()+ " wrote successfully");
			}
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void delete(Connection conn) {
		String SQL = "DELETE FROM finish_colors WHERE finish_color_sku = " + getcNumber();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
			pstmt.execute();
			System.out.println(getcNumber() + " Colors deleted");
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public String toString() {
		return "Color [descriptionEnglish=" + descriptionEnglish + ", descriptionSpanish=" + descriptionSpanish
				+ ", descriptionFrench=" + descriptionFrench + ", complimentary1=" + complimentary1
				+ ", complimentary2=" + complimentary2 + ", complimentary3=" + complimentary3 + ", imageSolid125="
				+ imageSolid125 + ", imageSolid145=" + imageSolid145 + ", imageSolid300=" + imageSolid300 + ", cnumber="
				+ cnumber + ", ccost=" + ccost + "]";
	}

}
