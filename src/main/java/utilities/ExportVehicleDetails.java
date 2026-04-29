package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportVehicleDetails {

    private static final String FILE_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/testData/ElectricCars.xlsx";

    public static void writeElectricCarDetails(
            List<String> carNames,
            List<String> prices,
            List<String> emis) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ElectricCars");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Car Name");
        header.createCell(1).setCellValue("Price");
        header.createCell(2).setCellValue("EMI");

        int rowCount = Math.min(
                carNames.size(),
                Math.min(prices.size(), emis.size())
        );

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(carNames.get(i));
            row.createCell(1).setCellValue(prices.get(i));
            row.createCell(2).setCellValue(emis.get(i));
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}