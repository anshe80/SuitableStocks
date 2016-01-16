package com.shen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

/**
 * ѡ���ֻ��Ʊ������ͨ��������ҵ��ѡ��Ҳ�����ֶ����ù�Ʊ
 * 
 * @author heshanshan
 * 
 */
public class Stocks extends AllStocks {
	/* basic info is code and name */
	private HashMap<String, String> stockBasicInfo = new HashMap<String, String>();
	private String tradeName;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Stocks stocks = new Stocks("hbgc");
		// System.out.println(stocks.stockBasicInfo);
		System.out.println(Util.getStockName("002672"));
	}

	public Stocks() {
		stockBasicInfo.put("300152", "ȼ�ؿƼ�");
		/*
		 * stockBasicInfo.put("300334", "��Ĥ�Ƽ�"); stockBasicInfo.put("300125", "������"); stockBasicInfo.put("300332",
		 * "�캾���� "); stockBasicInfo.put("300335", "��ɭ�ɷ�"); stockBasicInfo.put("603126", "�вĽ���");
		 * stockBasicInfo.put("300137", "�Ⱥӻ���"); stockBasicInfo.put("603588", "���ܻ���"); stockBasicInfo.put("002341",
		 * "���ڿƼ�"); stockBasicInfo.put("002573", "���»���"); stockBasicInfo.put("000939", "���ϵ���");
		 * stockBasicInfo.put("300187", "���廷��"); stockBasicInfo.put("600292", "�е�Զ��"); stockBasicInfo.put("300090",
		 * "ʢ�˻���"); stockBasicInfo.put("000035", "�й����"); stockBasicInfo.put("300172", "�е绷��");
		 * stockBasicInfo.put("300422", "������"); stockBasicInfo.put("000826", "ɣ�»���"); stockBasicInfo.put("300055",
		 * "����"); stockBasicInfo.put("002672", "��������"); stockBasicInfo.put("300156", "������");
		 * stockBasicInfo.put("603568", "ΰ������"); stockBasicInfo.put("300425", "���ܿƼ�"); stockBasicInfo.put("600217",
		 * "*ST����"); stockBasicInfo.put("300495", "������̬"); stockBasicInfo.put("300262", "�Ͱ�ˮ��");
		 * stockBasicInfo.put("300203", "�۹�Ƽ�"); stockBasicInfo.put("300190", "ά����"); stockBasicInfo.put("300070",
		 * "��ˮԴ"); stockBasicInfo.put("300056", "��ά˿");
		 */
	}

	Stocks(String tradeName) {
		// if (getStockCodeAndName(tradeName) != null) {
		// System.out.println("Get stock code and name about tade " + tradeName + "successful");
		// }
		if (Util.checkChinese(tradeName)) {
			this.tradeName = AllTrades.tradeShortAndName.get(tradeName);
		} else {
			this.tradeName = tradeName;
		}
	}

	/**
	 * get stock code and name about trade
	 * 
	 * @return
	 * @throws JSONException
	 */
	public Boolean getStockCodeAndName() throws JSONException {
		List<String> stockCode = getStockCode();
		String name = null;

		if (tradeName == null || stockCode == null) {
			return Boolean.FALSE;
		}

		for (String code : stockCode) {
			name = Util.getStockName(code);
			if (name != null) {
				stockBasicInfo.put(code, name);
			}
		}
		return Boolean.TRUE;
	}

	private List<String> getStockCode() throws JSONException {
		String body = null;
		List<String> stockCodes = new ArrayList<String>();
		JSONObject jsonObject = null;
		JSONArray menu = null;

		/* if tradeName is null return null */
		if (tradeName == null) {
			return null;
		}

		/* get stockcode in circle */
		for (int i = 1;; i++) {
			String url = "http://q.10jqka.com.cn/interface/stock/detail/zdf/desc/" + i + "/1/" + tradeName;

			try {
				/* get json type date */
				body = Jsoup.connect(url).ignoreContentType(true).execute().body();
				jsonObject = new JSONObject(body);

				/* transfer to jsonarray */
				menu = jsonObject.getJSONArray("data");

				/* if jsonarray is null than break */
				if (menu.length() <= 0) {
					break;
				}

				/* get stock code */
				for (int j = 0; j < menu.length(); j++) {
					String s = (String) menu.getJSONObject(j).get("stockcode");
					stockCodes.add(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stockCodes;
	}

	public HashMap<String, String> getstockBasicInfo() {
		return stockBasicInfo;
	}

	public void setstockBasicInfo(HashMap<String, String> stockBasicInfo) {
		this.stockBasicInfo = stockBasicInfo;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
}
