package utilities;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportScooterNames {

    private static final String FILE_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/testData/TVSScooters.xlsx";

    public static void writeScooterNames(List<String> scooterNames) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("TVS Scooters");

        // Header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Scooter Name");

        for (int i = 0; i < scooterNames.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(scooterNames.get(i));
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}