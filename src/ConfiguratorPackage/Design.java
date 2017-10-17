package ConfiguratorPackage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Design{
	
	//variable field
	private double designMoney;
	private double additionalCost;
	private double totalCost;
	private int width;
	private double widthMetric;
	private int designNumber;
	private String indicatesColection;
	private String image1;
	private String image2;
	private String descriptionEnglish1;
	private String descriptionEnglish2;
	private String descriptionEnglish3;
	private String descriptionSpanish1;
	private String descriptionSpanish2;
	private String descriptionSpanish3;
	private String descriptionFrench1;
	private String descriptionFrench2;
	private String descriptionFrench3;
	private double pg1;
	private double pg2;
	private double pg3;
	private double pg4;
	private double pg5;
	private double squareFootage;

	//object variable field
	private SpecialFeature specialFeature;
	private Orientation orientation;
	private Top top;
	private FinishMethod finishMethod;
	private Color color;
	
	//constant variable
	private static final int TWODECIMAL = 2;
	
	//constructor field
	
	/**
	 * default constructor
	 */
	public Design() {

	}
	
	/**
	 * base money, and additional cost constructor, 3-arg constructor
	 * @param designMoney
	 * @param additionalCost
	 */
	public Design(String image1, String image2, String indicatesCollection, int designNumber, int width, double widthMetric,
			String de1, String de2, String de3, String ds1, String ds2, String ds3,
			String df1, String df2, String df3, double pg1, double pg2, double pg3, double pg4, double pg5, double squareFootage ) {
		setImage1(image1);
		setImage2(image2);
		setIndicatesCollection(indicatesCollection);
		setDesignNumber(designNumber);
		setWidth(width);
		setWidthMetric(widthMetric);
		setDescriptionEnglish1(de1);
		setDescriptionEnglish2(de2);
		setDescriptionEnglish3(de3);
		setDescriptionSpanish1(ds1);
		setDescriptionSpanish2(ds2);
		setDescriptionSpanish3(ds3);
		setDescriptionFrench1(df1);
		setDescriptionFrench2(df2);
		setDescriptionFrench3(df3);
		setSquareFootage(squareFootage);
		setPg1(pg1);
		setPg2(pg2);
		setPg3(pg3);
		setPg4(pg4);
		setPg5(pg5);
		
	}
	
	//setters field
	
	/**
	 * set width 
	 * @param width
	 */
	public void setWidth(int width) throws IllegalArgumentException  {
		if(width < 19 || width > 133) {
			throw new IllegalArgumentException("Width can not be less than 19, or greater than 133");
		}
		this.width = width;
	}
	
	/**
	 * setter for base Money, set the argument baseMoney, to the class variable BaseMoney
	 * @param baseMoney
	 */
	public void setDesignMoney (double designMoney )throws IllegalArgumentException {
		if(designMoney < 0) {
			throw new IllegalArgumentException("Starting cost can not be less than zero");
		}
		this.designMoney = designMoney;
	}
	
	/**
	 * setter for design money
	 * @param designNumber
	 */
	public void setDesignNumber(int designNumber)throws IllegalArgumentException {
		if((designNumber >= 1000 && designNumber <= 10000) || designNumber == 0) {
			this.designNumber = designNumber;
		} else {
			throw new IllegalArgumentException("The design number needs to be with in 5000 and 10000");
		}
	}
		
	
	/**
	 * setter for indicates collection
	 * @param indicatesCollection
	 * @throws Exception
	 */
	public void setIndicatesCollection(String indicatesCollection) throws IllegalArgumentException {
		indicatesCollection.trim();
		indicatesCollection.toUpperCase();
		if(indicatesCollection.equals("A") || indicatesCollection.equals("B") || indicatesCollection.equals("N")) {
			this.indicatesColection = indicatesCollection;
		} else {
			throw new IllegalArgumentException("The indicates Collection can only be A or B");
		}
	}
	
	public void setWidthMetric(double widthMetric) {
		this.widthMetric = widthMetric;
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

	public void setDescriptionEnglish2(String descriptionEnglish2) {
		this.descriptionEnglish2 = descriptionEnglish2;
	}

	public void setDescriptionEnglish3(String descriptionEnglish3) {
		this.descriptionEnglish3 = descriptionEnglish3;
	}

	public void setDescriptionSpanish1(String descriptionSpanish1) {
		this.descriptionSpanish1 = descriptionSpanish1;
	}

	public void setDescriptionSpanish2(String descriptionSpanish2) {
		this.descriptionSpanish2 = descriptionSpanish2;
	}

	public void setDescriptionSpanish3(String descriptionSpanish3) {
		this.descriptionSpanish3 = descriptionSpanish3;
	}

	public void setDescriptionFrench1(String descriptionFrench1) {
		this.descriptionFrench1 = descriptionFrench1;
	}

	public void setDescriptionFrench2(String descriptionFrench2) {
		this.descriptionFrench2 = descriptionFrench2;
	}

	public void setDescriptionFrench3(String descriptionFrench3) {
		this.descriptionFrench3 = descriptionFrench3;
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
	
	public void setSquareFootage(double sf) {
		this.squareFootage = sf;
	}
	
	//getters field
	
	/**
	 * getter of class variable baseMoney
	 * @return
	 */
	public double getDesignMoney() {
		return this.designMoney;
	}
	
	/**
	 * setter for additional cost, set the argument baseMoney, to the class vairable BaseMoney
	 * @param additionalCost
	 */
	public void setAdditionalCost() throws IllegalArgumentException {
		
		this.additionalCost = specialFeature.getsfCost() + orientation.getoCost() + top.gettCost() + finishMethod.getfCost() + color.getcCost() ;
		
	}
	
	/**
	 * getter for additional cost.
	 * @return additionalCost
	 */
	public double getAdditionalCost() {
		return round(this.additionalCost, TWODECIMAL);
	}
	
	/**
	 * round the total cost into two decimals, then return the value
	 * @return totalCost
	 */
	public double getTotalCost() {
		return round(this.totalCost, TWODECIMAL);
	}
	
	/**
	 * design number getter
	 * @return this.designNumber
	 */
	public int getDesignNumber() {
		return this.designNumber;
	}
	
	/**
	 * indicates collection getter
	 * @return this.indicatesCollection
	 */
	public String getIndicatesCollection() {
		return this.indicatesColection;
	}
	
	/**
	 * width getter
	 * @return this.width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * getter for Orientation referenced object
	 * @return newOrientation
	 */
	public Orientation getOrientation() {
		Orientation newOrientation = orientation;
		return newOrientation;
	}
	
	/**
	 * getter for Special Feature referenced object
	 * @return newSpecialFeature
	 */
	public SpecialFeature getSpecialFeature() {
		SpecialFeature newSpecialFeature = specialFeature;
		return newSpecialFeature;
	}
	
	/**
	 * getter for Color referenced object
	 * @return newColor
	 */
	public Color getColor() {
		Color newColor = color;
		return newColor;
	}
	
	/**
	 * getter for Top referenced object
	 * @return newTop
	 */
	public Top getTop() {
		Top newTop = top;
		return newTop;
	}
	
	/**
	 * getter for Finished Method referenced object 
	 * @return newFinishMethod
	 */
	public FinishMethod getFinishMethod(){
		FinishMethod newFinishMethod = finishMethod;
		return newFinishMethod;
	}
	
	public double getWidthMetric() {
		return widthMetric;
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

	public String getDescriptionEnglish2() {
		return descriptionEnglish2;
	}

	public String getDescriptionEnglish3() {
		return descriptionEnglish3;
	}

	public String getDescriptionSpanish1() {
		return descriptionSpanish1;
	}

	public String getDescriptionSpanish2() {
		return descriptionSpanish2;
	}

	public String getDescriptionSpanish3() {
		return descriptionSpanish3;
	}

	public String getDescriptionFrench1() {
		return descriptionFrench1;
	}

	public String getDescriptionFrench2() {
		return descriptionFrench2;
	}

	public String getDescriptionFrench3() {
		return descriptionFrench3;
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
	
	public double getSquareFootage() {
		return squareFootage;
	}
	
	//behavior field
	
	/**
	 * calculation of the total cost, by using class variables base money, and additional cost
	 * total cost equals to the sum of base money and addiontal cost.
	 */
	public void totalCostCalculation() {
		this.totalCost = this.designMoney + this.additionalCost;
	}
	
	/**
	 * rounding for the total cost into a two decimal number, since money will only go down to pennies
	 * throwing illegalArgumentException 
	 * @param totalCost
	 * @param rounding
	 * @return
	 */
	public double round(double totalCost, int rounding) throws IllegalArgumentException{
		if(rounding < 0) {
			throw new IllegalArgumentException("Invalid rounding number, can not be zero");
		}
		
		long factor = (long) Math.pow(10,rounding);
		totalCost = totalCost * factor;
		long temp = Math.round(totalCost);
		return (double) temp / factor;
	}
	
	/**
	 * setting the reference variable orientation with Orientation constructor
	 * @param orientationName
	 * @param orientationCost
	 */
	public void addOrientation(String orientationName, double orientationCost) {
		orientation = new Orientation(orientationName, orientationCost);
	}
	
	/**
	 * setting the reference variable specialFeature with SpecialFeature constructor
	 * @param specialFeatureName
	 * @param specialFeatureCost
	 */
	public void addSpecialFeature(String sfName, String descriptionEnglish1, String descriptionSpanish1,
			String descriptionFrench1,double pg1, double pg2, double pg3, double pg4, double pg5,String image1, String image2) {
		specialFeature = new SpecialFeature(sfName, descriptionEnglish1,descriptionSpanish1,
				descriptionFrench1,pg1,pg2,pg3,pg4,pg5,image1, image2);
	}
	
	/**
	 * setting the reference variable top with Top constructor
	 * @param topName
	 * @param topCost
	 */
	public void addTop(String tname,String de1, String ds1, String df1, double wpbsf, double p1, double p2, double p3, double p4, double p5,
			String image1, String image2) {
		top = new Top(tname,de1,ds1,df1,wpbsf,p1,p2,p3,p4,p5,image1,image2);
	}
	
	/**
	 * setting the reference variable color with Color constructor
	 * @param colorNumber
	 * @param colorCost
	 */
	public void addColor(int cnumber,String english, String spanish, String french, String compilmentary1, String compilmentary2, String compilmentary3, double pg1,double pg2, double pg3, double pg4, double pg5,String image125,String image145, String image300) {
		color = new Color(cnumber,english, spanish, french, compilmentary1, compilmentary2, compilmentary3, pg1,pg2, pg3, pg4, pg5,image125,image145, image300);
	}
	
	/**
	 * setting the reference variable finishMethod with FinishMethod constructor
	 * @param finishMethodName
	 * @param finishMethodCost
	 */
	public void addFinishMethod(String image1, String image2, String fname, String descriptionEnglish1, String descriptionSpanish1,
			String descriptionFrench1, String descriptionEnglish2, String descriptionSpanish2, String descriptionFrench2, double pg1,
			double pg2, double pg3, double pg4, double pg5) {
		finishMethod = new FinishMethod(image1,image2,fname,descriptionEnglish1,descriptionSpanish1,descriptionFrench1,descriptionEnglish2,descriptionSpanish2,descriptionFrench2,pg1,
				pg2,pg3,pg4,pg5);
	}
	
	public void write(Connection conn) {
		String SQL ="INSERT INTO designs("
				+ "image_1," + "image_2,"
				+ "collection," + "design_number,"
				+ "width_english," + "width_metric,"
				+ "description_1_english," + "description_2_english," + "description_3_english,"
				+ "description_1_spanish," + "description_2_spanish," + "description_3_spanish,"
				+ "description_1_french," + "description_2_french," + "description_3_french,"
				+ "group_price_1," + "group_price_2," + "group_price_3," + "group_price_4," + "group_price_5,"
				+ "sf) "
				+ "VALUES("
				+ "?," + "?,"
				+ "?," + "?,"
				+ "?," + "?,"
				+ "?," + "?," + "?,"
				+ "?," + "?," + "?,"
				+ "?," + "?," + "?,"
				+"?," + "?," + "?," + "?," + "?,"
				+ "?)";
		try(PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
			pstmt.setString(1,getImage1());
			pstmt.setString(2,getImage2());
			pstmt.setString(3,getIndicatesCollection());
			pstmt.setInt(4, getDesignNumber());
			pstmt.setInt(5,getWidth());
			pstmt.setDouble(6, getWidthMetric());
			pstmt.setString(7, getDescriptionEnglish1());
			pstmt.setString(8, getDescriptionEnglish2());
			pstmt.setString(9, getDescriptionEnglish3());
			pstmt.setString(10, getDescriptionSpanish1());
			pstmt.setString(11, getDescriptionSpanish2());
			pstmt.setString(12, getDescriptionSpanish3());
			pstmt.setString(13, getDescriptionFrench1());
			pstmt.setString(14, getDescriptionFrench2());
			pstmt.setString(15, getDescriptionFrench3());
			pstmt.setBigDecimal(16, getPg1());
			pstmt.setBigDecimal(17, getPg2());
			pstmt.setBigDecimal(18, getPg3());
			pstmt.setBigDecimal(19, getPg4());
			pstmt.setBigDecimal(20, getPg5());
			pstmt.setDouble(21, getSquareFootage());
			
			if(pstmt.execute()) {
				System.out.println(getDesignNumber() + " wrote successfully");
			}
			
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void delete(Connection conn) {
		String SQL = "DELETE FROM designs WHERE design_number = " + getDesignNumber();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
			pstmt.execute();
			System.out.println(getDesignNumber()+ " Designs deleted");
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public String toString() {
		return "Design [designMoney=" + designMoney + ", additionalCost=" + additionalCost + ", totalCost=" + totalCost
				+ ", width=" + width + ", widthMetric=" + widthMetric + ", designNumber=" + designNumber
				+ ", indicatesColection=" + indicatesColection + ", image1=" + image1 + ", image2=" + image2
				+ ", descriptionEnglish1=" + descriptionEnglish1 + ", descriptionEnglish2=" + descriptionEnglish2
				+ ", descriptionEnglish3=" + descriptionEnglish3 + ", descriptionSpanish1=" + descriptionSpanish1
				+ ", descriptionSpanish2=" + descriptionSpanish2 + ", descriptionSpanish3=" + descriptionSpanish3
				+ ", descriptionFrench1=" + descriptionFrench1 + ", descriptionFrench2=" + descriptionFrench2
				+ ", descriptionFrench3=" + descriptionFrench3 + ", pg1=" + pg1 + ", pg2=" + pg2 + ", pg3=" + pg3
				+ ", pg4=" + pg4 + ", pg5=" + pg5 + ", squareFootage=" + squareFootage ;
	}

	
	
}
