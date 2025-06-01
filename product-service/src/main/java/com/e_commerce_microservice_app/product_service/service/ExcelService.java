package com.e_commerce_microservice_app.product_service.service;

import com.e_commerce_microservice_app.product_service.dto.ExcelMetadataDto;
import com.e_commerce_microservice_app.product_service.dto.ResourceDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.jdbc.Work;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    public ResourceDto exportExcel(ExcelMetadataDto excelMetadataDto)  {
        Resource resource = prepareExcel(excelMetadataDto);
        final ResourceDto resourceDto = new ResourceDto();
        resourceDto.setResource(resource);
        resourceDto.setMediaType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        return resourceDto;
    }

    private Resource prepareExcel(ExcelMetadataDto excelMetadataDto) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(excelMetadataDto.getTableName());

        prepareHeaders(workbook, sheet, excelMetadataDto.getHeaders());
        fillTable(workbook, sheet, excelMetadataDto.getDatas(), excelMetadataDto.getHeaders());

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            workbook.write(byteArrayOutputStream);

            return new ByteArrayResource((byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR");
        }
    }

    private void fillTable(Workbook workbook, Sheet sheet, List<Map<String, String>> datas, List<String> headers){
        int rowNo = 1;
        Font font = workbook.createFont();
        font.setFontName("Arial");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        for(Map<String, String> data : datas){
            Row row = sheet.createRow(rowNo);
            for(int columnNo = 0; columnNo< headers.size(); columnNo++){
                fillCell(sheet, row, columnNo
                String.valueOf(data.get(headers.get(columnNo))), cellStyle);
            }
            rowNo++;
        }
    }

    private void fillCell(Sheet sheet, Row row, int columnNo, String value, CellStyle cellStyle){
        Cell cell = row.createCell(columnNo);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
        sheet.autoSizeColumn(columnNo);
    }

    private void prepareHeaders(Workbook workbook, Sheet sheet, List<String> headers){
        Row headerRow = sheet.createRow(0);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Arial");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        int columnNo = 0;
        for(String header : headers){
            Cell headerCell = headerRow.createCell(columnNo++);
            headerCell.setCellValue(header);
            headerCell.setCellStyle(cellStyle);
        }
    }
}
