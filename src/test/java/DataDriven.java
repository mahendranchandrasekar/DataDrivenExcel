import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

    //Identify Testcases column by scanning the entire 1st row
    //Once Column is identified then Scan entire testcase Column to identify PURCHASE testcase row
   //After you grab Purchase testcase Row = pull all the data of that Row and feed into Test
public class DataDriven {
    @Test
    public ArrayList<String> getData(String testcaseName) throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/ExcelData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        System.out.println(sheets);
        for(int i=0;i<sheets;i++){
            if(workbook.getSheetName(i).equalsIgnoreCase("testdata")){
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();  //control pass to the First Row
                Iterator<Cell> cv1 = firstRow.cellIterator(); //this iterator contains all the cells of First Row



                int k=0;
                int column=0;
                while(cv1.hasNext()){       // to check whether Next Cell is present or NOT
                    Cell value = cv1.next(); //control pass to the First Cell
                    if(value.getStringCellValue().equalsIgnoreCase("TestCases")){
                        column=k;
                    }
                    k++;
                }
               System.out.println(column); //to get the No of Columns in Excel

        // Once column is identified then Scan entire Testcase column to identify Purchase Testcase Row
                while(rows.hasNext()){
                    Row r = rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)){
        //After you grab Purchase testcase Row = pull all the data of that Row and feed into Test
                    Iterator<Cell> cv2 =r.cellIterator();
                    while(cv2.hasNext()){
                        Cell cv3 = cv2.next();
                        if(cv3.getCellType()== CellType.STRING) {
                        arrayList.add(cv3.getStringCellValue());
                    }else {
                            arrayList.add(NumberToTextConverter.toText(cv3.getNumericCellValue()));
                        }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

}
