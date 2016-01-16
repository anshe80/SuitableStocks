package com.shen;

import java.util.HashMap;

/**
 * useful indexs
 * 
 * @author heshanshan
 * 
 */
public class Indexs {
	private HashMap<String, Integer> chineseToInterger = new HashMap<String, Integer>();
	private HashMap<String, String> englishWordToChinese = new HashMap<String, String>();
	private static Indexs indexs = new Indexs();

	public static void main(String[] args) {
		System.out.println(indexs.getEnglishWordToChinese().values());
	}

	private Indexs() {
		chineseToInterger.put("��Ŀ\\ʱ��(��)", 1);// "years");
		chineseToInterger.put("����ÿ������(Ԫ)", 2);// "basicEarningsPerShare");
		chineseToInterger.put("������(��Ԫ)", 3);// "netProfit");
		chineseToInterger.put("������ͬ��������(%)", 4);// "netProfitGrowthRat");
		chineseToInterger.put("Ӫҵ������(��Ԫ)", 5);// "operationRevenue");
		chineseToInterger.put("Ӫҵ������ͬ��������(%)", 6);// "operationRevenueGrowthRat");
		chineseToInterger.put("ÿ�ɾ��ʲ�(Ԫ)", 7);// "netAssetsPerShare");
		chineseToInterger.put("���ʲ�������(%)", 8);// "liability");
		chineseToInterger.put("ÿ���ʱ�������(Ԫ)����", 9);// "eachCapitalReserveFund");
		chineseToInterger.put("ÿ��δ��������(Ԫ)", 10);// "nonDistributionProfitPerShare");
		chineseToInterger.put("ÿ�ɾ�Ӫ�ֽ���(Ԫ)", 11);// "operatingCashFlowPerShare");
		chineseToInterger.put("����ë����(%)", 12);// "grossProfitMargin");
		chineseToInterger.put("�����ת��(%)", 13);// "inventoryTurnOver");

		englishWordToChinese.put("years", "��Ŀ\\ʱ��(��)");
		englishWordToChinese.put("basicEarningsPerShare", "����ÿ������(Ԫ)");
		englishWordToChinese.put("netProfit", "������(��Ԫ)");
		englishWordToChinese.put("netProfitGrowthRat", "������ͬ��������(%)");
		englishWordToChinese.put("operationRevenue", "Ӫҵ������(��Ԫ)");
		englishWordToChinese.put("operationRevenueGrowthRat", "Ӫҵ������ͬ��������(%)");
		englishWordToChinese.put("netAssetsPerShare", "ÿ�ɾ��ʲ�(Ԫ)");
		englishWordToChinese.put("liability", "���ʲ�������(%)");
		englishWordToChinese.put("eachCapitalReserveFund", "ÿ���ʱ�������(Ԫ)����");
		englishWordToChinese.put("nonDistributionProfitPerShare", "ÿ��δ��������(Ԫ)");
		englishWordToChinese.put("operatingCashFlowPerShare", "ÿ�ɾ�Ӫ�ֽ���(Ԫ)");
		englishWordToChinese.put("grossProfitMargin", "����ë����(%)");
		englishWordToChinese.put("inventoryTurnOver", "�����ת��(%)");
	}

	public String getKeyFromValueOngetEnglishWordToChinese(String value) {
		for (String name : indexs.getEnglishWordToChinese().keySet()) {
			if (indexs.getEnglishWordToChinese().get(name).equals(value)) {
				return name;
			}
		}
		return null;
	}

	public static Indexs getIndexs() {
		return indexs;
	}

	public HashMap<String, Integer> getChineseToInterger() {
		return chineseToInterger;
	}

	public void setChineseToInterger(HashMap<String, Integer> chineseToInterger) {
		this.chineseToInterger = chineseToInterger;
	}

	public HashMap<String, String> getEnglishWordToChinese() {
		return englishWordToChinese;
	}

	public void setEnglishWordToChinese(HashMap<String, String> englishWordToChinese) {
		this.englishWordToChinese = englishWordToChinese;
	}
}
