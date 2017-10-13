package ConfiguratorPackage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FinishMethod{
	private String fname;
	private double fcost;
	private String image1;
	private String image2;
	private String descriptionEnglish1;
	private String descriptionSpanish1;
	private String descriptionFrench1;
	private String descriptionEnglish2;
	private String descriptionSpanish2;
	private String descriptionFrench2;
	private double pg1;
	private double pg2;
	private double pg3;
	private double pg4;
	private double pg5;
	
	public FinishMethod() {
		
	}
	
	public FinishMethod(String image1, String image2, String fname, String descriptionEnglish1, String descriptionSpanish1,
			String descriptionFrench1, String descriptionEnglish2, String descriptionSpanish2, String descriptionFrench2, double pg1,
			double pg2, double pg3, double pg4, double pg5) {
	 	setImage1(image1);
	 	setImage2(image2);
	 	setfName(fname);
	 	setDescriptionEnglish1(descriptionEnglish1);
	 	setDescriptionSpanish1(descriptionSpanish1);
	 	setDescriptionFrench1(descriptionFrench1);
	 	setDescriptionEnglish2(descriptionEnglish2);
	 	setDescriptionSpanish2(descriptionSpanish2);
	 	setDescriptionFrench2(descriptionFrench2);
	 	setPg1(pg1);
	 	setPg2(pg2);
	 	setPg3(pg3);
	 	setPg4(pg4);
	 	setPg5(pg5);
 	}
 
 	public void setfName(String fname){
 		fname.trim();
 		fname = fname.toUpperCase();
 		if(fname.equals("V") || fname.equals("S")) {
 			this.fname = fname;
 		} else {
 			throw new IllegalArgumentException("Finish method has to be either Vintage or Solid");
 		}
 	}
 	
 	public String getfName() {
 		return this.fname;
 	}
 	
 	public void setfCost(double fcost) {
 		if(fcost < 0) {
 			throw new IllegalArgumentException("Cost can not be negative");
 		}
 		else {	
 			this.fcost=fcost;
 		}
 	}

 	public double getfCost() {
 		return this.fcost;
 	}

	public String getImage1() {
		return image1;
	}

	public String getImage2() {
		return image2;
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

	public String getDescriptionEnglish2() {
		return descriptionEnglish2;
	}

	public String getDescriptionSpanish2() {
		return descriptionSpanish2;
	}

	public String getDescriptionFrench2() {
		return descriptionFrench2;
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

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
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

	public void setDescriptionEnglish2(String descriptionEnglish2) {
		this.descriptionEnglish2 = descriptionEnglish2;
	}

	public void setDescriptionSpanish2(String descriptionSpanish2) {
		this.descriptionSpanish2 = descriptionSpanish2;
	}

	public void setDescriptionFrench2(String descriptionFrench2) {
		this.descriptionFrench2 = descriptionFrench2;
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
	
	public void write(Connection conn) {
		String SQL ="INSERT INTO finish_methods("
				+ "finish_image_1," + "finish_image_2,"
				+ "finish_method," 
				+ "description_1_english," + "description_1_spanish," + "description_1_french,"
				+ "description_2_english," + "description_2_spanish," + "description_2_french,"
				+ "pricing_group_1," + "pricing_group_2," + "pricing_group_3," + "pricing_group_4," + "pricing_group_5) "
				+ "VALUES("
				+ "?,?,"
				+ "?,"
				+ "?,?,?,"
				+ "?,?,?,"
				+ "?,?,?,?,?)";
		
		try(PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)){
			pstmt.setString(1, getImage1());
			pstmt.setString(2, getImage2());
			pstmt.setString(3, getfName());
			pstmt.setString(4, getDescriptionEnglish1());
			pstmt.setString(5, getDescriptionSpanish1());
			pstmt.setString(6, getDescriptionFrench1());
			pstmt.setString(7, getDescriptionEnglish2());
			pstmt.setString(8, getDescriptionSpanish2());
			pstmt.setString(9, getDescriptionFrench2());
			pstmt.setBigDecimal(10, getPg1());
			pstmt.setBigDecimal(11, getPg2());
			pstmt.setBigDecimal(12, getPg3());
			pstmt.setBigDecimal(13, getPg4());
			pstmt.setBigDecimal(14, getPg5());
			if(pstmt.execute()) {
				System.out.println(getfName() + " wrote successfully");
			}
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void delete(Connection conn) {
		
	}

	@Override
	public String toString() {
		return "FinishMethod [fname=" + fname + ", image1=" + image1 + ", image2=" + image2 + ", descriptionEnglish1="
				+ descriptionEnglish1 + ", descriptionSpanish1=" + descriptionSpanish1 + ", descriptionFrench1="
				+ descriptionFrench1 + ", descriptionEnglish2=" + descriptionEnglish2 + ", descriptionSpanish2="
				+ descriptionSpanish2 + ", descriptionFrench2=" + descriptionFrench2 + ", pg1=" + pg1 + ", pg2=" + pg2
				+ ", pg3=" + pg3 + ", pg4=" + pg4 + ", pg5=" + pg5;
	}
	
	
}
