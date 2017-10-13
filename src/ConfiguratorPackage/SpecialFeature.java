package ConfiguratorPackage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SpecialFeature{
	private String image1;
	private String image2;
	private String descriptionEnglish1;

	private String descriptionSpanish1;

	private String descriptionFrench1;
	
	private double pg1;
	private double pg2;
	private double pg3;
	private double pg4;
	private double pg5;
	private String sfname;
	private double sfcost;
	
 	public SpecialFeature() {
 	}
	
 	public SpecialFeature(String sfName, String descriptionEnglish1, String descriptionSpanish1,
			String descriptionFrench1,double pg1, double pg2, double pg3, double pg4, double pg5, String image1, String image2) {
		
		setImage1(image1);
		setImage2(image2);
		setDescriptionEnglish1(descriptionEnglish1);
		setDescriptionSpanish1(descriptionSpanish1);
		setDescriptionFrench1(descriptionFrench1);
		setPg1(pg1);
		setPg2(pg2);
		setPg3(pg3);
		setPg4(pg4);
		setPg5(pg5);
		setsfName(sfName);
 	}
 
 	public void setsfName(String sfname){
 		sfname = sfname.toUpperCase();
 		if(sfname.equals("WFR")||sfname.equals("WFL")||sfname.equals("NON")||sfname.equals("DEFAULT")){
 		this.sfname= sfname;
 		}
 		else {
 			throw new IllegalArgumentException("Invalid name");
 		}
 	}
 	
 	public String getsfName() {
 		return this.sfname;
 	}
 	
 	public void setsfCost(double sfcost) {
 		if(sfcost < 0) {
 			throw new IllegalArgumentException("Cost can not be negative");
 		}
 		else {	
 			this.sfcost=sfcost;
 		}
 	}

 	public String getImage1() {
		return image1;
	}
 	
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	
	public String getImage2() {
		return image2;
	}
	
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	public String getDescriptionEnglish1() {
		return descriptionEnglish1;
	}
	
	public void setDescriptionEnglish1(String descriptionEnglish1) {
		this.descriptionEnglish1 = descriptionEnglish1;
	}
	
		
	public String getDescriptionSpanish1() {
		return descriptionSpanish1;
	}
	
	public void setDescriptionSpanish1(String descriptionSpanish1) {
		this.descriptionSpanish1 = descriptionSpanish1;
	}
	
	
	public String getDescriptionFrench1() {
		return descriptionFrench1;
	}
	
	public void setDescriptionFrench1(String descriptionFrench1) {
		this.descriptionFrench1 = descriptionFrench1;
	}
	
	
	
	public BigDecimal getPg1() {
		BigDecimal copyPg1 = new BigDecimal(pg1);
		return copyPg1;
	}
	
	public void setPg1(double pg1) {
		this.pg1 = pg1;
	}
	
	public BigDecimal getPg2() {
		BigDecimal copyPg2 = new BigDecimal(pg2);
		return copyPg2;
	}
	
	public void setPg2(double pg2) {
		this.pg2 = pg2;
	}
	
	public BigDecimal getPg3() {
		BigDecimal copyPg3 = new BigDecimal(pg3);
		return copyPg3;
	}
	
	public void setPg3(double pg3) {
		this.pg3 = pg3;
	}
	
	public BigDecimal getPg4() {
		BigDecimal copyPg4 = new BigDecimal(pg4);
		return copyPg4;
	}
	
	public void setPg4(double pg4) {
		this.pg4 = pg4;
	}
	
	public BigDecimal getPg5() {
		return new BigDecimal(pg5);
	}
	
	public void setPg5(double pg5) {
		this.pg5 = pg5;
	}	
	
	public double getsfCost() {
 		return this.sfcost;
 	}
	
	public void write(Connection conn) {
		String SQL = "INSERT INTO special_features("
				+ "special_feature_id,"
				+ "description_1_english," + "description_1_spanish," + "description_1_french,"
				+ "price_group_1," + "price_group_2," + "price_group_3," + "price_group_4," + "price_group_5,"
				+ "image_1," + "image_2) "
				+ "VALUES("
				+"?,"
				+"?,?,?,"
				+"?,?,?,?,?,"
				+"?,?)";
		
		try( PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
			pstmt.setString(1,getsfName());
			pstmt.setString(2, getDescriptionEnglish1());
			pstmt.setString(3, getDescriptionSpanish1());
			pstmt.setString(4, getDescriptionFrench1());
			pstmt.setBigDecimal(5, getPg1());
			pstmt.setBigDecimal(6, getPg2());
			pstmt.setBigDecimal(7, getPg3());
			pstmt.setBigDecimal(8, getPg4());
			pstmt.setBigDecimal(9, getPg5());
			pstmt.setString(10, getImage1());
			pstmt.setString(11, getImage2());
			if(pstmt.execute()) {
				System.out.println(getsfName()+ " wrote successfully");
			}
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		} 
		
	}

	@Override
	public String toString() {
		return "SpecialFeature [image1=" + image1 + ", image2=" + image2 + ", descriptionEnglish1="
				+ descriptionEnglish1 + ", descriptionSpanish1=" + descriptionSpanish1 + ", descriptionFrench1="
				+ descriptionFrench1 + ", pg1=" + pg1 + ", pg2=" + pg2 + ", pg3=" + pg3 + ", pg4=" + pg4 + ", pg5="
				+ pg5 + ", sfname=" + sfname;
	}
	
}
