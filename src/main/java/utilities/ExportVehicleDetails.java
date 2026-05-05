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
    public static void writeCngCarRatingDetails(
            List<String> carNames,
            List<Double> ratings) throws IOException {

        String filePath =
                System.getProperty("user.dir")
                        + "/src/test/resources/testData/CngCarsRatingGreaterThan_4.xlsx";

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("CNG Cars Rating > 4");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Car Name");
        header.createCell(1).setCellValue("Rating");

        for (int i = 0; i < carNames.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(carNames.get(i));
            row.createCell(1).setCellValue(ratings.get(i));
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }
    private static final String FILE_PATH2 =
            System.getProperty("user.dir")
                    + "/src/test/resources/testData/UsedCars_PopularModels.xlsx";

    public static void writePopularModelsToExcel(
            List<String> popularModels) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Popular Used Cars");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Popular Used Car Models");

        for (int i = 0; i < popularModels.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(popularModels.get(i));
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH2)) {
            workbook.write(fos);
        }

        workbook.close();
    }


    private static final String FILE_PATH1 =
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

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH1)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    private static final String FILE_PATH3 =
            System.getProperty("user.dir")
                    + "/src/test/resources/testData/HondaUpcomingBikes.xlsx";

    public static void writeHondaBikeDetails(List<String> hondaBikeNames) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("HondaUpcomingBikes");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Honda Bike Name");

        for (int i = 0; i < hondaBikeNames.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(hondaBikeNames.get(i));
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH3)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}