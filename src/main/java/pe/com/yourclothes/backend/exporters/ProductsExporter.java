package pe.com.yourclothes.backend.exporters;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.com.yourclothes.backend.entities.Product;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductsExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Product> productList;

    public ProductsExporter(List<Product> productList) {
        this.productList = productList;
        workbook = new XSSFWorkbook();
    }

    public void createCell(Row row, int column, Object value, CellStyle style)
    {
        sheet.autoSizeColumn(column);
        Cell cell = row.createCell(column);

        if(value instanceof Integer){
            cell.setCellValue((Integer)value);
        }else if (value instanceof Double){
            cell.setCellValue((Double)value);
        }else if (value instanceof Boolean){
            cell.setCellValue((Boolean)value);
        }else if (value instanceof Long){
            cell.setCellValue((Long)value);
        }else if (value instanceof String){
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);

    }
    public void writeHeaderLine()
    {
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);


        createCell(row, 0, "Id", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Brand", style);
        createCell(row, 3, "Shop-Name", style);
        createCell(row, 4, "Price", style);
        createCell(row, 5, "Condition", style);
        createCell(row, 6, "Price-Type", style);
    }

    public void writeDataLines()
    {
        int rowcount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(12);
        style.setFont(font);

        for(Product product: productList)
        {
            Row row = sheet.createRow(rowcount);
            int colcount = 0;
            createCell(row, colcount, product.getId(), style);
            createCell(row, colcount+1, product.getName(), style);
            createCell(row, colcount+2, product.getBrand(), style);
            createCell(row, colcount+3, product.getShopname(), style);
            createCell(row, colcount+4, product.getPrice(), style);
            createCell(row, colcount+5, product.getCondition(), style);
            createCell(row, colcount+6, product.getPricetype(), style);
            rowcount++;
        }
    }

    public void writeFooterLine()
    {

    }

    public void export(HttpServletResponse response) throws IOException {
        sheet = workbook.createSheet("Products");

        writeHeaderLine();
        writeDataLines();
        writeFooterLine();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }

}
