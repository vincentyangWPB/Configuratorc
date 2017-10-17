package ExcelReader;
import ConfiguratorPackage.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import DataBaseDrivers.DataBaseConnection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {
	private XSSFRow row;
	private String excelPath;
	
	//excel arraylist
	private ArrayList<Design> excelDesignList = new ArrayList<>();
	private ArrayList<SpecialFeature> excelSpecialFeatureList = new ArrayList<>();
	private ArrayList<Color> excelColorList = new ArrayList<>();
	private ArrayList<Top> excelTopColorList = new ArrayList<>();
	private ArrayList<FinishMethod> excelFinishMethodList = new ArrayList<>();
	
	//postgres arraylist
	private ArrayList<Design> sqlDesignList = new ArrayList<>();
	private ArrayList<SpecialFeature> sqlSpecialFeatureList = new ArrayList<>();
	private ArrayList<Color> sqlColorList = new ArrayList<>();
	private ArrayList<Top> sqlTopColorList = new ArrayList<>();
	private ArrayList<FinishMethod> sqlFinishMethodList = new ArrayList<>();
	
	public static void main(String[] args) {
		ExcelDriver myNewExcelDriver = new ExcelDriver();
		try {
			myNewExcelDriver.readFromPostgres();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ExcelDriver() {
		excelPath = "empty";
	}
	
	public ExcelDriver(String excelPath) {
		if(excelPath.isEmpty()) {
			throw new IllegalArgumentException("Invalid Impelmentation Excel Path");
		} else {
			this.excelPath = excelPath;
		}
	}
	
	public boolean excelToPostgres() throws Exception {
		try {
			DataBaseConnection myDB = new DataBaseConnection();
			myDB.openConnection();
			
			File f = new File(excelPath);
			FileInputStream inputStream = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			
			if(f.isFile() && f.exists()) {
				System.out.println("FIle exist");
			} else {
				System.out.println("File doesnt exist");
			}
			//reading from excel
			readForDesign(workbook);
			readForSpecialFeature(workbook);
			readForColor(workbook);
			readForTopColor(workbook);
			readForFinishMethod(workbook);
			
			//writting 
			writeDesigns(myDB.getConn());
			writeSpecialFeatures(myDB.getConn());
			writeColor(myDB.getConn());
			writeTopColor(myDB.getConn());
			writeFinishMethods(myDB.getConn());
			
			//delete
			deleteColor(myDB.getConn());
			inputStream.close();
			myDB.closeConnection();
			return true;
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		}	
	}
	
	public void readFromPostgres() throws Exception{
		try {
			DataBaseConnection myDB = new DataBaseConnection();
			myDB.openConnection();
			
			//read from postgress
			sqlDesignsReader(myDB.getConn());
			sqlColorReader(myDB.getConn());
			sqlFinishMethodReader(myDB.getConn());
			sqlSpecialFeatureReader(myDB.getConn());
			sqlTopColorReader(myDB.getConn());
			
			for(Design d : sqlDesignList) {
				System.out.println(d.toString());
			}
			for(Color c : sqlColorList) {
				System.out.println(c.toString());
			}
			for(FinishMethod fm : sqlFinishMethodList) {
				System.out.println(fm.toString());
			}
			for(SpecialFeature sf : sqlSpecialFeatureList) {
				System.out.println(sf.toString());
			}
			for(Top t : sqlTopColorList) {
				System.out.println(t.toString());
			}
			
		} catch(Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
		}
	}
	
	//excel reading methods
	@SuppressWarnings("deprecation")
	public void readForDesign(XSSFWorkbook workbook) {
		XSSFSheet spreadsheet = workbook.getSheetAt(2);
		Iterator <Row> rowIterator = spreadsheet.iterator();
		int counter = 0;
		while(rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			if(row.getRowNum() >1 &&  row.getRowNum() <= 236 ) {
				Iterator <Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					try {
						String image1 = cellIterator.next().getStringCellValue();
						String image2 = cellIterator.next().getStringCellValue();
						String collection = cellIterator.next().getStringCellValue();
						int designNumber = (int)cellIterator.next().getNumericCellValue();
						int widthEnglish = (int)cellIterator.next().getNumericCellValue();
						double widthMetric = cellIterator.next().getNumericCellValue();
						String descriptionEnglish1 = cellIterator.next().getStringCellValue();
						String descriptionEnglish2 = cellIterator.next().getStringCellValue();
						String descriptionEnglish3 = cellIterator.next().getStringCellValue();
						String descriptionSpanish1 = cellIterator.next().getStringCellValue();
						String descriptionSpanish2 = cellIterator.next().getStringCellValue();
						String descriptionSpanish3 = cellIterator.next().getStringCellValue();
						String descriptionFrench1 = cellIterator.next().getStringCellValue();
						String descriptionFrench2 = cellIterator.next().getStringCellValue();
						String descriptionFrench3 = cellIterator.next().getStringCellValue();
						double pg1 = cellIterator.next().getNumericCellValue();
						double pg2 = cellIterator.next().getNumericCellValue();
						double pg3 = cellIterator.next().getNumericCellValue();
						double pg4 = cellIterator.next().getNumericCellValue();
						double pg5 = cellIterator.next().getNumericCellValue();
						double squareFootage = cellIterator.next().getNumericCellValue();
						Design myDesign = new Design(image1,image2,collection,designNumber,widthEnglish,widthMetric,descriptionEnglish1,descriptionEnglish2,
								descriptionEnglish3,descriptionSpanish1,descriptionSpanish2,descriptionSpanish3,descriptionFrench1,descriptionFrench2,descriptionFrench3,
								pg1,pg2,pg3,pg4,pg5,squareFootage);
						excelDesignList.add(myDesign);
						System.out.println(counter);
						counter++;
						if(cellIterator.next().getCellType() != 1 || cellIterator.next().getCellType() !=0) {
							break;
						}
					} catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
				System.out.println("Design insert successed");
			}
		}
		System.out.println("Design insert done");
	}
	
	@SuppressWarnings("deprecation")
	public void readForSpecialFeature(XSSFWorkbook workbook) {
		
		XSSFSheet spreadsheet = workbook.getSheetAt(4);
		Iterator <Row> rowIterator = spreadsheet.iterator();
		while(rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			if(row.getRowNum() >1 && row.getRowNum() <= 3){
				Iterator <Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					try {	
						String specialFeatureId = cellIterator.next().getStringCellValue().trim();
						String descriptionEnglish = cellIterator.next().getStringCellValue();
						String descriptionSpanish = cellIterator.next().getStringCellValue();
						String descriptionFrench = cellIterator.next().getStringCellValue();
						double pg1 = cellIterator.next().getNumericCellValue();
						double pg2 = cellIterator.next().getNumericCellValue();
						double pg3 = cellIterator.next().getNumericCellValue();
						double pg4 = cellIterator.next().getNumericCellValue();
						double pg5 = cellIterator.next().getNumericCellValue();
						String image1 = cellIterator.next().getStringCellValue();
						String image2 = cellIterator.next().getStringCellValue();
						SpecialFeature mySpecialFeature = new SpecialFeature(specialFeatureId, descriptionEnglish, descriptionSpanish, descriptionFrench, pg1, pg2, pg3, pg4, pg5, image1, image2);
						excelSpecialFeatureList.add(mySpecialFeature);
						if(cellIterator.next().getCellType() != 1 || cellIterator.next().getCellType() !=0) {
							break;
						}
					}  catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
				System.out.println("Special Feature insert successed");
			}
		}
		System.out.println("Special Feature insert done");
	}
	
	@SuppressWarnings("deprecation")
	public void readForColor(XSSFWorkbook workbook) {
		XSSFSheet spreadsheet = workbook.getSheetAt(5);
		Iterator<Row> rowIterator = spreadsheet.rowIterator();
		int counter = 0;
		while(rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			if(row.getRowNum() >1 && row.getRowNum() <= 21) {
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					try {
							int finishColorSKU = (int)cellIterator.next().getNumericCellValue();
							String descriptionEnglish = cellIterator.next().getStringCellValue();
							String descriptionSpanish = cellIterator.next().getStringCellValue();
							String descriptionFrench = cellIterator.next().getStringCellValue();
							String compilmentary1 = cellIterator.next().getStringCellValue();
							String compilmentary2 = cellIterator.next().getStringCellValue();
							String compilmentary3 = cellIterator.next().getStringCellValue();
							double pg1 = cellIterator.next().getNumericCellValue();
							double pg2 = cellIterator.next().getNumericCellValue();
							double pg3 = cellIterator.next().getNumericCellValue();
							double pg4 = cellIterator.next().getNumericCellValue();
							double pg5 = cellIterator.next().getNumericCellValue();
							String image125 = cellIterator.next().getStringCellValue();
							String image145 = cellIterator.next().getStringCellValue();
							String image300 = cellIterator.next().getStringCellValue();
							Color newColor = new Color(finishColorSKU, descriptionEnglish, descriptionSpanish, descriptionFrench, compilmentary1, compilmentary2, compilmentary3, pg1, pg2, pg3, pg4, pg5, image125, image145, image300);
							System.out.println(counter);
							counter++;
							excelColorList.add(newColor);
							if(cellIterator.next().getCellType() != 1 || cellIterator.next().getCellType() !=0) {
								break;
							}
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}	
				}
				System.out.println("Finish Color insert successed");
			}
		}
		System.out.println("Finish Color insert done\n");
	}
	
	public void readForTopColor(XSSFWorkbook workbook) {
		
		XSSFSheet spreadsheet = workbook.getSheetAt(7);
		Iterator <Row> rowIterator = spreadsheet.iterator();
		while(rowIterator.hasNext() ) {
			row = (XSSFRow) rowIterator.next();
			if(row.getRowNum()>= 2 && row.getRowNum()<=6) {
				Iterator <Cell> cellIterator= row.cellIterator();
				while(cellIterator.hasNext()) {
					try {	
						String ty= cellIterator.next().getStringCellValue();
						String description_1_english= cellIterator.next().getStringCellValue();
						String description_1_spanish= cellIterator.next().getStringCellValue();
						String description_1_french= cellIterator.next().getStringCellValue();
						double wpbsf= cellIterator.next().getNumericCellValue();
						double pricing_group_1= cellIterator.next().getNumericCellValue();
						double pricing_group_2= cellIterator.next().getNumericCellValue();
						double pricing_group_3= cellIterator.next().getNumericCellValue();
						double pricing_group_4= cellIterator.next().getNumericCellValue();
						double pricing_group_5= cellIterator.next().getNumericCellValue();
						String image_1= cellIterator.next().getStringCellValue();
						String image_2= cellIterator.next().getStringCellValue();
						
	//					System.out.println(ty + description_1_english+ description_1_spanish + description_1_french + wpbsf + pricing_group_1 + pricing_group_2 + pricing_group_3 + pricing_group_4 + pricing_group_5 + image_1 + image_2 );
						
						Top newTopColor = new Top(ty, description_1_english, description_1_spanish, description_1_french, wpbsf, pricing_group_1, pricing_group_2, pricing_group_3, pricing_group_4, pricing_group_5, image_1, image_2 );
						excelTopColorList.add(newTopColor);
						if(cellIterator.next().getCellType() != 0 || cellIterator.next().getCellType() != 1) {
							break;
						}
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					System.out.println("Top Color insert successed");
				}
			}
			
		}
		System.out.println("Top Color insert done");
	}
	
	public void readForFinishMethod(XSSFWorkbook workbook) {
		XSSFSheet spreadsheet = workbook.getSheetAt(6);
		Iterator <Row> rowIterator = spreadsheet.iterator();
		while(rowIterator.hasNext()) {
			row = (XSSFRow)rowIterator.next();
			if(row.getRowNum() >=2 && row.getRowNum() <= 3) {
				Iterator <Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					try {
						String image1 = cellIterator.next().getStringCellValue();
						String image2 = cellIterator.next().getStringCellValue();
						String finishMethod = cellIterator.next().getStringCellValue();
						String descriptionEnglish1 = cellIterator.next().getStringCellValue();
						String descriptionSpanish1 = cellIterator.next().getStringCellValue();
						String descriptionFrench1 = cellIterator.next().getStringCellValue();
						String descriptionEnglish2 = cellIterator.next().getStringCellValue();
						String descriptionSpanish2 = cellIterator.next().getStringCellValue();
						String descriptionFrench2 = cellIterator.next().getStringCellValue();
						double pg1 = cellIterator.next().getNumericCellValue();
						double pg2 = cellIterator.next().getNumericCellValue();
						double pg3 = cellIterator.next().getNumericCellValue();
						double pg4 = cellIterator.next().getNumericCellValue();
						double pg5 = cellIterator.next().getNumericCellValue();
						FinishMethod newFinishMethod = new FinishMethod(image1, image2, finishMethod, descriptionEnglish1, descriptionSpanish1, descriptionFrench1, descriptionEnglish2, descriptionSpanish2, descriptionFrench2, pg1, pg2, pg3, pg4, pg5);
						excelFinishMethodList.add(newFinishMethod);
						if(cellIterator.next().getCellType() != 0 || cellIterator.next().getCellType() != 1) {
							break;
						}
					} catch(Exception e) {
						System.err.println(e.getMessage());
					}	
				}
				System.out.println("Finish Method insert successed");
			}
		}
		System.out.println("Finish Method insert done");
	}
	
	//read from database
	public void sqlDesignsReader(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM designs;");
			while(rs.next()) {
				String image1 = rs.getString("image_1");
				String image2 = rs.getString("image_2");
				String collection =  rs.getString("collection");
				int designNumber = rs.getInt("design_number");
				int width = rs.getInt("width_english");
				double widthMetric = rs.getDouble("width_metric");
				String de1 = rs.getString("description_1_english");
				String de2 = rs.getString("description_2_english");
				String de3 = rs.getString("description_3_english");
				String ds1 = rs.getString("description_1_spanish");
				String ds2 = rs.getString("description_2_spanish");
				String ds3 = rs.getString("description_3_spanish");
				String df1 = rs.getString("description_1_french");
				String df2 = rs.getString("description_2_french");
				String df3 = rs.getString("description_3_french");
				double pg1 = rs.getBigDecimal("group_price_1").doubleValue();
				double pg2 = rs.getBigDecimal("group_price_2").doubleValue();
				double pg3 = rs.getBigDecimal("group_price_3").doubleValue();
				double pg4 = rs.getBigDecimal("group_price_4").doubleValue();
				double pg5 = rs.getBigDecimal("group_price_5").doubleValue();
				double squareFootage = rs.getDouble("sf");
				
				Design newDesign = new Design(image1, image2, collection, designNumber, width, widthMetric, de1, de2, de3, ds1, ds2, ds3, df1, df2, df3, pg1, pg2, pg3, pg4, pg5, squareFootage);
				sqlDesignList.add(newDesign);
			}
		} catch(Exception ex) {
			System.err.println(ex.getClass().getName()+": " + ex.getMessage());
			ex.getStackTrace();
		}
		System.out.println("designs read SQL done successfully");
	}
	
	public void sqlSpecialFeatureReader(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM special_features");
			while(rs.next()) {
				String specialFeatureId = rs.getString("special_feature_id");
				String de1 = rs.getString("description_1_english");
				String ds1 = rs.getString("description_1_spanish");
				String df1 = rs.getString("description_1_french");
				double pg1 = rs.getBigDecimal("price_group_1").doubleValue();
				double pg2 = rs.getBigDecimal("price_group_2").doubleValue();
				double pg3 = rs.getBigDecimal("prcie_group_3").doubleValue();
				double pg4 = rs.getBigDecimal("price_group_4").doubleValue();
				double pg5 = rs.getBigDecimal("price_group_5").doubleValue();
				String image1 = rs.getString("image_1");
				String image2 = rs.getString("image_2");
				SpecialFeature newSpecialFeature = new SpecialFeature(specialFeatureId, de1, ds1, df1, pg1, pg2, pg3, pg4, pg5, image1, image2);
				
				sqlSpecialFeatureList.add(newSpecialFeature);
			}
		} catch(Exception ex) {
			System.err.print(ex.getClass().getName()+": " + ex.getMessage());
		}
		System.out.println("special feature SQL read done successfully");
	}
	
	public void sqlColorReader(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM finish_colors;");
			while(rs.next()) {
				int finishColorSku = rs.getInt("finish_color_sku");
				String descriptionEnglish1 = rs.getString("description_1_english");
				String descriptionSpanish1 = rs.getString("description_1_spanish");
				String descriptionFrench1 = rs.getString("description_1_french");
				String complimentary1 = rs.getString("complimentary_1");
				String complimentary2 = rs.getString("complimentary_2");
				String complimentary3 = rs.getString("complimentary_3");
				double pg1 = rs.getBigDecimal("price_group_1").doubleValue();
				double pg2 = rs.getBigDecimal("price_group_2").doubleValue();
				double pg3 = rs.getBigDecimal("price_group_3").doubleValue();
				double pg4 = rs.getBigDecimal("price_group_4").doubleValue();
				double pg5 = rs.getBigDecimal("price_group_5").doubleValue();
				String image1 = rs.getString("image_1");
				String image2 = rs.getString("image_2");
				String image3 = "EMPTY";
				Color newColor = new Color(finishColorSku, descriptionEnglish1, descriptionSpanish1, descriptionFrench1, complimentary1, complimentary2, complimentary3, pg1, pg2, pg3, pg4, pg5, image1, image2, image3);
				sqlColorList.add(newColor);
			}
		} catch(Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
		}
		
		System.out.println("finish color SQL read done successfully");
	}
	
	public void sqlFinishMethodReader(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM finish_methods");
			while(rs.next()) {
				String image1 = rs.getString("finish_image_1");
				String image2 = rs.getString("finish_image_2");
				String finishMethod = rs.getString("finish_method");
				String descriptionEnglish1 = rs.getString("description_1_english");
				String descriptionSpanish1 = rs.getString("description_1_spanish");
				String descriptionFrench1 = rs.getString("description_1_french");
				String descriptionEnglish2 = rs.getString("description_2_english");
				String descriptionSpanish2 = rs.getString("description_2_spanish");
				String descriptionFrench2 = rs.getString("description_2_french");
				double pg1 = rs.getBigDecimal("pricing_group_1").doubleValue();
				double pg2 = rs.getBigDecimal("pricing_group_2").doubleValue();
				double pg3 = rs.getBigDecimal("pricing_group_3").doubleValue();
				double pg4 = rs.getBigDecimal("pricing_group_4").doubleValue();
				double pg5 = rs.getBigDecimal("pricing_group_5").doubleValue();
				FinishMethod newFinishMethod = new FinishMethod(image1, image2, finishMethod, descriptionEnglish1, descriptionSpanish1, descriptionFrench1, descriptionEnglish2, descriptionSpanish2, descriptionFrench2, pg1, pg2, pg3, pg4, pg5);
				sqlFinishMethodList.add(newFinishMethod);
			}
		} catch(Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
		}
		System.out.println("finish method SQL read done successfully");
	}
	
	public void sqlTopColorReader(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM top_colors");
			while(rs.next()) {
				String topColor = rs.getString("ty");
				String descriptionEnglish1 = rs.getString("description_1_english");
				String descriptionSpanish1 = rs.getString("description_1_Spanish");
				String descriptionFrench1 = rs.getString("description_1_french");
				int wpbsf = rs.getInt("wpbsf");
				double pg1 = rs.getBigDecimal("pricing_group_1").doubleValue();
				double pg2 = rs.getBigDecimal("pricing_group_2").doubleValue();
				double pg3 = rs.getBigDecimal("pricing_group_3").doubleValue();
				double pg4 = rs.getBigDecimal("pricing_group_4").doubleValue();
				double pg5 = rs.getBigDecimal("pricing_group_5").doubleValue();
				String image1 = rs.getString("image_1");
				String image2 = rs.getString("image_2");
				Top newTop = new Top(topColor, descriptionEnglish1, descriptionSpanish1, descriptionFrench1, wpbsf, pg1, pg2, pg3, pg4, pg5, image1, image2);
				sqlTopColorList.add(newTop);
			}
		} catch(Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
		}
		System.out.println("top color SQL read done successfully");
	}
	
	//writting methods
	public void writeDesigns(Connection conn) {
		for(Design design : excelDesignList) {
			design.write(conn);
		}
		System.out.println("Designs done writting");
	}
	
	public void writeSpecialFeatures(Connection conn) {
		for(SpecialFeature specialFeature : excelSpecialFeatureList) {
			specialFeature.write(conn);
		}
		System.out.println("Speical Features done writting");
	}
	
	public void writeColor(Connection conn) {
		for(Color color : excelColorList) {
			color.write(conn);
		}
		System.out.println("Color done writting");
	}
	
	public void writeTopColor(Connection conn) {
		for(Top topColor : excelTopColorList) {
			topColor.write(conn);
		}
		System.out.println("Top Color done writting");
	}
	
	public void writeFinishMethods(Connection conn) {
		for(FinishMethod finishMethod :  excelFinishMethodList) {
			finishMethod.write(conn);
		}
		System.out.println("Finish Method done writting");
	}
	
	//deleting methods
	public void deleteColor(Connection conn) {
		for(Color deleteColor : sqlColorList) {
			deleteColor.delete(conn);
		}
		System.out.println("Color content deleted");
	}
	
	public void deleteDesign(Connection conn) {
		for(Design deleteDesign : sqlDesignList) {
			deleteDesign.delete(conn);
		}
		System.out.println("Design content deleted");
	}
	
	public void deleteSpecialFeature(Connection conn) {
		for(SpecialFeature deleteSpecialFeature : sqlSpecialFeatureList) {
			deleteSpecialFeature.delete(conn);
		}
		System.out.println("Special Featuer content deleted");
	}
	
	public void deleteFinishMethod(Connection conn) {
		for(FinishMethod deleteFinishMethod : sqlFinishMethodList) {
			deleteFinishMethod.delete(conn);
		}
		System.out.println("Finish Method content deleted");
	}
	
	public void deleteTopColor(Connection conn) {
		for(Top deleteTopColor :  sqlTopColorList) {
			deleteTopColor.delete(conn);
		}
		System.out.println("Top Color content deleted");
	}
	
}

