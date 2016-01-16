package com.shen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * produce report about tonghuashun trade
 * 
 * @author heshanshan
 * 
 */
public class CreateReport {
	public final static int years = 10;// count how many years data
	private static String filePath = "d:\\stocks"; // store report to this absolute path
	private static String LAST_FIVE_YEARS_VARA = "������귽��";
	private static String LAST_FIVE_YEARS_AVERAGE = "�������ƽ��ֵ";

	public static void main(String[] args) {
		CreateReport createReport = new CreateReport();
		ParseStockInfoFromXLS parseStockInfoXLS = new ParseStockInfoFromXLS();
		StockInfo stockInfo = new StockInfo("002672");
		Stocks stocks = new Stocks();
		stocks.getstockBasicInfo().put("002672", "��������");

		List<StockInfo> list = new ArrayList<StockInfo>();
		list.add(stockInfo);

		try {
			parseStockInfoXLS.parse(stockInfo);
			createReport.writeExcel(list, stocks);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * write report to excel
	 * 
	 * @param list
	 * @param stocks
	 */
	public void writeExcel(List<StockInfo> list, Stocks stocks) {
		WritableWorkbook book = null;
		WritableSheet sheet = null;
		Indexs indexs = Indexs.getIndexs();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int sheetNum = 0;
		int column = 0;
		int row = 0;
		char startColumn = 'A' + years - 5;
		char endColumn = 'A' + years - 1;
		/* �����ƽ��ֵͳ������ */
		String countRegion = null;
		String fileName = filePath + File.separator;
		if (stocks.getTradeName() != null) {
			fileName += AllTrades.tradeShortAndName.get(stocks.getTradeName()) + ".xls";
		} else {
			fileName += "reports.xls";
		}

		try {
			// ���ļ�
			book = Workbook.createWorkbook(new File(fileName));
			for (String key : indexs.getChineseToInterger().keySet()) {
				System.out.println(key);
				/* ����Ҫ��ݵ�sheet */
				if (key.equals("��Ŀ\\ʱ��(��)")) {
					continue;
				}

				/* ������Ϊkey�Ĺ���������0��ʾ���ǵ�һҳ */
				sheet = book.createSheet(key, sheetNum++);
				CellView cellView = new CellView();
				cellView.setAutosize(true); // �����Զ���С

				sheet.addCell(new Label(0, 0, "���"));

				/* ������ݱ����� */
				for (int i = 1; i < years; i++) {
					sheet.addCell(new Number(i, 0, Double.valueOf(currentYear - years + i)));
				}

				sheet.setColumnView(years + 2, cellView);// ���������Զ������п�
				sheet.setColumnView(years + 3, cellView);// ���������Զ������п�
				/* �����ƽ��ֵ������ */
				sheet.addCell(new Label(years + 2, 0, LAST_FIVE_YEARS_VARA));
				sheet.addCell(new Label(years + 3, 0, LAST_FIVE_YEARS_AVERAGE));

				if (list != null && !list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						/* ��ù�Ʊ���� */
						String stockCode = list.get(i).getStockCode();
						/* ���ݹ�Ʊ�����ù�Ʊ���� */
						sheet.addCell(new Label(0, i + 1, stocks.getstockBasicInfo().get(stockCode)));

						/* ����ָ��ĺ��ֻ��Ӣ�ĵ��� */
						String englishword = indexs.getKeyFromValueOngetEnglishWordToChinese(key);
						List<String> values = list.get(i).getInfos().get(englishword);
						column = years;
						row = i + 1;

						countRegion = "" + startColumn + (row + 1) + ":" + endColumn + (row + 1);

						/* д���Ʊ���ָ��ָ */
						for (int j = 0; j < values.size(); j++) {
							String value = values.get(j);
							if (!value.equals("null")) {
								sheet.addCell(new Number(--column, row, Double.valueOf(value)));
							}
						}

						/* д�뷽���ƽ��ֵ */
						sheet.addCell(new Formula(years + 2, row, "VARA(" + countRegion + ")"));
						sheet.addCell(new Formula(years + 3, row, "AVERAGE(" + countRegion + ")"));
					}
				}
			}

			// д�����ݲ��ر��ļ�
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (book != null) {
				try {
					book.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
