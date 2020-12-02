import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class test_exel {
    @Test
    public void t1() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("D:\\atguigu\\practice\\java.xlsx");
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                String stringCellValue = cell.getStringCellValue();
                System.out.println(stringCellValue);
            }
        }
        xssfWorkbook.close();
    }


    @Test
    public void t2() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("hzwz");


        for (int i = 0; i <3; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 3; j++) {
                row.createCell(j).setCellValue("耗子尾汁");

            }

        }
        FileOutputStream outputStream = new FileOutputStream("D:\\atguigu\\practice\\java.xlsx");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }
}
