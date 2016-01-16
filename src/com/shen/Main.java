package com.shen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import jxl.read.biff.BiffException;

public class Main {

	public static void main(String[] args) {
		/*
		 * "��ɫ�ҵ� �뵼�弰Ԫ�� ��װӡˢ ���ռ����� �ɾ���� ��ý ���� �����豸 �������� ���ز����� ��֯���� ���������� ��װ�ҷ� ���� �ۿں��� ���� ��·��·���� ��ѧ����� �������� �����ϳɲ��� �����²���
		 * ��ѧ��Ʒ ��ѧ��ҩ �������� �������� ������ѧ ������豸 �����Ӧ�� �����Ṥ �������� ����װ�� �����豸���� ���㼰���� �Ƶ꼰���� ���� ó�� ú̿���� ũ��Ʒ�ӹ� ũҵ���� �������� �����㲿�� ��������
		 * ȼ��ˮ�� ������Ʒ ʯ�Ϳ�ҵ���� ʳƷ�ӹ����� �������� ͨ�ŷ��� ͨ���豸 ͨ���豸 ���� �²��� ��ֳҵ ҽ����е���� ҽҩ��ҵ �����Ǳ� ���� �������� ��ɫұ���ӹ� ԰������ ��ֽ ֤ȯ ��ҩ ��ֲҵ����ҵ
		 * ר���豸 �ۺ�" ;
		 */
		Stocks stocks = new Stocks("����");
		try {
			stocks.getStockCodeAndName();
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		DownLoadStockXLS downLoadStockInfo = new DownLoadStockXLS();
		CreateReport createReport = new CreateReport();
		ParseStockInfoFromXLS parseStockInfoXLS = new ParseStockInfoFromXLS();
		List<StockInfo> stockinfos = new ArrayList<StockInfo>();

		for (String stockCode : stocks.getstockBasicInfo().keySet()) {
			StockInfo stockInfo = new StockInfo(stockCode);
			stockinfos.add(stockInfo);

			if (!downLoadStockInfo.downdLoadXLS(stockInfo)) {
				System.out.println("down load xls about " + stockInfo.getStockCode() + " failed");
			}

			try {
				parseStockInfoXLS.parse(stockInfo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		createReport.writeExcel(stockinfos, stocks);
	}
}
