package ExcelReader;
import ConfiguratorPackage.*;
import java.io.*;
import java.sql.Connection;
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
	private ArrayList<Design> designList = new ArrayList<>();
	private ArrayList<SpecialFeature> specialFeatureList = new ArrayList<>();
	private ArrayList<Color> colorList = new ArrayList<>();
	private ArrayList<Top> topColorList = new ArrayList<>();
	private ArrayList<FinishMethod> finishMethodList = new ArrayList<>();
	
	public static void main(String[] ags) throws Exception{
		ExcelDriver myExcel = new ExcelDriver("G:\\Job\\Database\\Updated Catalog Spec REV10.xlsx");
		try {
			System.out.println(myExcel.excelToPostgres());
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
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
			inputStream.close();
			myDB.closeConnection();
			return true;
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		}
			
	}
	
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
						designList.add(myDesign);
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
						specialFeatureList.add(mySpecialFeature);
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
							colorList.add(newColor);
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
						topColorList.add(newTopColor);
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
						finishMethodList.add(newFinishMethod);
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
	
	//writting methods
	public void writeDesigns(Connection conn) {
		for(Design design : designList) {
			design.write(conn);
		}
		System.out.println("Designs done writting");
	}
	
	public void writeSpecialFeatures(Connection conn) {
		for(SpecialFeature specialFeature : specialFeatureList) {
			specialFeature.write(conn);
		}
		System.out.println("Speical Features done writting");
	}
	
	public void writeColor(Connection conn) {
		for(Color color : colorList) {
			color.write(conn);
		}
		System.out.println("Color done writting");
	}
	
	public void writeTopColor(Connection conn) {
		for(Top topColor : topColorList) {
			topColor.write(conn);
		}
		System.out.println("Top Color done writting");
	}
	
	public void writeFinishMethods(Connection conn) {
		for(FinishMethod finishMethod :  finishMethodList) {
			finishMethod.write(conn);
		}
		System.out.println("Finish Method done writting");
	}
}

