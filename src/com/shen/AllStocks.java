package com.shen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * all stocks's code and name, contains szse and sse
 * 
 * @author heshanshan
 * 
 */
public abstract class AllStocks {
	static HashMap<String, String> allStockCodeAndName = init();

	public static HashMap<String, String> init() {
		jxl.Workbook readwb = null;
		HashMap<String, String> hashMap = new HashMap<String, String>();
		InputStream instream;
		try {
			instream = AllStocks.class.getResourceAsStream("/" + "���й�˾�б�.xls");

			readwb = Workbook.getWorkbook(instream);

			// Sheet���±��Ǵ�0��ʼ
			// ��ȡ��һ��Sheet��
			Sheet readsheet = readwb.getSheet(0);

			// ��ȡSheet������������������,Ŀǰֻ��Ҫ���������
			// int columns = 2;
			// readsheet.getColumns();

			// ��ȡSheet������������������
			int rsRows = readsheet.getRows();

			// ��ȡָ����Ԫ��Ķ�������
			for (int i = 0; i < rsRows; i++) {
				String key = readsheet.getCell(0, i).getContents();
				String value = readsheet.getCell(1, i).getContents();

				// System.out.println("code is " + key + " name is " + value);

				hashMap.put(key, value);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hashMap;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		HashMap<String, String> hashMap = AllStocks.allStockCodeAndName;
	}
}
