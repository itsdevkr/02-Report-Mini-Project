package com.ashokit.service;

import java.awt.Color;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.binding.CitizenPlan;
import com.ashokit.binding.SearchRequest;
import com.ashokit.repository.CitizenPlanRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private CitizenPlanRepository cpRepo;

	@Override
	public List<String> getPlanName() {
		return cpRepo.getPlanName();
	}

	@Override
	public List<String> getPlanStatus() {
		return cpRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> getCitizenPlan(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		
		if(request.getPlanName()!=null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(request.getPlanStatus()!=null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(request.getGender()!=null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}
		
		Example<CitizenPlan> example = Example.of(entity);
		
		List<CitizenPlan> records = cpRepo.findAll(example);
		
		return records;
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {
		
		List<CitizenPlan> records = cpRepo.findAll();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Citizen_Info");
		XSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Email");
		headerRow.createCell(3).setCellValue("Phone");
		headerRow.createCell(4).setCellValue("Plan Name");
		headerRow.createCell(5).setCellValue("Plan Status");
		int dataIndexRow = 1;
		
		for(CitizenPlan record: records) {
			XSSFRow dataRow = sheet.createRow(dataIndexRow);
			dataRow.createCell(0).setCellValue(record.getCid());
			dataRow.createCell(1).setCellValue(record.getCname());
			dataRow.createCell(2).setCellValue(record.getCemail());
			dataRow.createCell(3).setCellValue(record.getPhno());
			dataRow.createCell(4).setCellValue(record.getPlanName());
			dataRow.createCell(5).setCellValue(record.getPlanStatus());
			
			dataIndexRow++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception{
		List<CitizenPlan> records = cpRepo.findAll();
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("List of Users", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f, 1.5f});
		table.setSpacingBefore(10);
		
		//Set Table header data
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("ID", font1));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("Name", font1));
		

		table.addCell(cell);
		cell.setPhrase(new Phrase("Email", font1));
		

		table.addCell(cell);
		cell.setPhrase(new Phrase("Phone", font1));

		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Name", font1));

		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Status", font1));
		
		
		
		
		document.add(table);
		document.close();
		
		
	}
	
	
}
