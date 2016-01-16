package com.shen;

import java.util.HashMap;

public abstract class AllTrades {
	public static HashMap<String, String> tradeShortAndName = init();

	@SuppressWarnings("unused")
    public static void main(String[] args) {
		AllTrades allTrades = new AllTrades() {
		};
	}

	private static HashMap<String, String> init() {
		HashMap<String, String> hashMap = new HashMap<String, String>();

		String shorter = "bsjd bdtjyj bzys bxjqt cjfw cm dl dqsb dzzz fdckf fzzz fqcjy fzjf gt gkhy gj gltlys gxgdz gfjg hghccl hgxcl hxzp hxzy hbgc jchy jchx jsjsb jsjyy jyqg jzcl jzzs jysbfw jdjly jdjcy ls my mtkc ncpjg nyfw qtdz qclbj qczc rqsw swzp sykykc spjgzz stqc txfw txsb tysb wl xcl yzy ylqxfw yysy yqyb yx ylzz ysyljg yqkf zz zq zy zzyyly zysb zh";
		String name = "��ɫ�ҵ� �뵼�弰Ԫ�� ��װӡˢ ���ռ����� �ɾ���� ��ý ���� �����豸 �������� ���ز����� ��֯���� ���������� ��װ�ҷ� ���� �ۿں��� ���� ��·��·���� ��ѧ����� �������� �����ϳɲ��� �����²��� ��ѧ��Ʒ ��ѧ��ҩ �������� �������� ������ѧ ������豸 �����Ӧ�� �����Ṥ �������� ����װ�� �����豸���� ���㼰���� �Ƶ꼰���� ���� ó�� ú̿���� ũ��Ʒ�ӹ� ũҵ���� �������� �����㲿�� �������� ȼ��ˮ�� ������Ʒ ʯ�Ϳ�ҵ���� ʳƷ�ӹ����� �������� ͨ�ŷ��� ͨ���豸 ͨ���豸 ���� �²��� ��ֳҵ ҽ����е���� ҽҩ��ҵ �����Ǳ� ���� �������� ��ɫұ���ӹ� ԰������ ��ֽ ֤ȯ ��ҩ ��ֲҵ����ҵ ר���豸 �ۺ�";
		String[] shorterArray = shorter.split(" ");
		String[] nameArray = name.split(" ");
		int shorterLen = shorterArray.length;
		int nameLen = nameArray.length;

		if (shorterLen == nameLen) {
			for (int i = 0; i < shorterLen; i++) {
				hashMap.put(shorterArray[i], nameArray[i]);
				hashMap.put(nameArray[i], shorterArray[i]);
			}
		}
		System.out.println(hashMap);
		return hashMap;
	}

	public HashMap<String, String> getTradeShortAndName() {
		return tradeShortAndName;
	}

	public void setTradeShortAndName(HashMap<String, String> tradeShortAndName) {
		AllTrades.tradeShortAndName = tradeShortAndName;
	}

}
