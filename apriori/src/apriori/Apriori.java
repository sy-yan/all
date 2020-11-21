package apriori;
import java.io.*;
import java.util.*;
 
public class Apriori {
 
	static String filePath = "src/apriori/1.txt";
	static ArrayList<ArrayList<String>> D = new ArrayList<ArrayList<String>>();// �������ݿ�
	static HashMap<ArrayList<String>, Double> C = new HashMap<ArrayList<String>, Double>();// ��Ŀ��
	static HashMap<ArrayList<String>, Double> L = new HashMap<ArrayList<String>, Double>();// ��ѡ��
	static double min_support = 0.5;// ��С֧�ֶ�
	static double min_confident = 0.7;// ��С���Ŷ�
 
	// ���ڴ�ȡ��ѡ��ÿ�μ�������������������򣬾Ͳ����ٴα����������ݿ⣬��ô�鷳�ˡ�
	static HashMap<ArrayList<String>, Double> L_ALL = new HashMap<ArrayList<String>, Double>();
 
	/**
	 * ��txt�еĶ�ά�����T��
	 * 
	 * @param filePath
	 *            TXT���ļ�·����ע���ļ�·���ָ�������д��\\
	 * @return ArrayList<ArrayList<String>>�����ÿ��Ԫ��List��List
	 */
	public static ArrayList<ArrayList<String>> readTable(String filePath) {
		ArrayList<ArrayList<String>> T = new ArrayList<ArrayList<String>>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filePath));// ָ�������ļ�
			while (scanner.hasNext()) {
				String[] temp=scanner.nextLine()
						.split(" ");
				T.add(new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(temp,1 , temp.length))));
			}
			scanner.close();// ����ر�������������о��档
		} catch (FileNotFoundException e) {
			System.out.println("�ļ������ڣ�����·����");
		}
		return T;
	}
 
	// ��֦����ɾȥC������С֧�ֶȵ�Ԫ�أ��γ�L
	public static void pruning(HashMap<ArrayList<String>, Double> C,
			HashMap<ArrayList<String>, Double> L) {
		L.clear();
		// ������Ŀ�����ɺ�ѡ��
		L.putAll(C);
		// ɾ��������С֧�ֶȵ�Ԫ��
		ArrayList<ArrayList<String>> delete_key = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> key : L.keySet()) {
			if (L.get(key) < min_support) {
				delete_key.add(key);
			}
		}
		for (int i = 0; i < delete_key.size(); i++) {
			L.remove(delete_key.get(i));
		}
	}
 
	/**
	 * ��ʼ���������ݿ⡢��Ŀ������ѡ��
	 */
	public static void init() {
		D = readTable(filePath);
 
		// ɨ���������ݿ⡣������Ŀ����֧�ֶ�=��Ԫ�����������ݿ���ֵĴ���/�������ݿ��������
		for (int i = 0; i < D.size(); i++) {
			for (int j = 0; j < D.get(i).size(); j++) {
				String[] e = { D.get(i).get(j) };
				ArrayList<String> item = new ArrayList<String>(Arrays.asList(e));
				if (!C.containsKey(item)) {
					C.put(item, 1.0 / D.size());
				} else {
					C.put(item, C.get(item) + 1.0 / D.size());
				}
			}
		}
 
		pruning(C, L);// ��֦
 
		L_ALL.putAll(L);
 
	}
 
	// �����������󲢼�
	public static ArrayList<String> arrayListUnion(
			ArrayList<String> arraylist1, ArrayList<String> arraylist2) {
		ArrayList<String> arraylist = new ArrayList<String>();
		arraylist.addAll(arraylist1);
		arraylist.addAll(arraylist2);
		arraylist = new ArrayList<String>(new HashSet<String>(arraylist));
		return arraylist;
	}
 
	/**
	 * ����������յĺ�ѡƵ����
	 * 
	 * @param C
	 *            ��ɳ�ʼ������Ŀ��
	 * @param L
	 *            ��ɳ�ʼ���ĺ�ѡ��
	 * @return ���յĺ�ѡƵ����
	 */
	public static HashMap<ArrayList<String>, Double> iteration(
			HashMap<ArrayList<String>, Double> C,
			HashMap<ArrayList<String>, Double> L) {
		HashMap<ArrayList<String>, Double> L_temp = new HashMap<ArrayList<String>, Double>();// �����ж��Ƿ������֦����ʱ����
 
		int t = 1;// ��������
		while (L.size() > 0) {// һ������֦����ɾ�����֦֮ǰ��������Ҫ��Ľ����
			t++;
			L_temp.clear();
			L_temp.putAll(L);
			// һ�����Ӳ�
			C.clear();
			// 1.��L�е�����һ���Ĺ�������ƥ��
			ArrayList<ArrayList<String>> L_key = new ArrayList<ArrayList<String>>(
					L.keySet());
			for (int i = 0; i < L_key.size(); i++) {
				for (int j = i + 1; j < L_key.size(); j++) {
					ArrayList<String> C_item = new ArrayList<String>();
					C_item = new ArrayList<String>(arrayListUnion(L_key.get(i),
							L_key.get(j)));
					if (C_item.size() == t) {
						C.put(C_item, 0.0);// Ƶ��������зǿ��Ӽ���������Ƶ����
					}
				}
			}
			// 2.ͨ��ɨ��D����������֧�ֶ�
			for (ArrayList<String> key : C.keySet()) {
				for (int i = 0; i < D.size(); i++) {
					if (D.get(i).containsAll(key)) {
						C.put(key, C.get(key) + 1.0 / D.size());
					}
				}
			}
			// System.out.println(C);
			// ������֦��
			pruning(C, L);
			// System.out.println(L);
			// System.out.println("===");
 
			L_ALL.putAll(L);
		}
 
		return L_temp;
	}
 
	// ��һ�����ϵ������Ӽ�
	public static ArrayList<ArrayList<String>> getSubset(ArrayList<String> L) {
		if (L.size() > 0) {
			ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < Math.pow(2, L.size()); i++) {// �����Ӽ�����=2�ĸü��ϳ��ȵĳ˷�
				ArrayList<String> subSet = new ArrayList<String>();
				int index = i;// ������0һֱ��2�ļ��ϳ��ȵĳ˷�-1
				for (int j = 0; j < L.size(); j++) {
					// ͨ����һλ�ƣ��ж�������һλ��1������ǣ�����Ӵ���
					if ((index & 1) == 1) {// λ�����㣬�ж����һλ�Ƿ�Ϊ1
						subSet.add(L.get(j));
					}
					index >>= 1;// ��������һλ
				}
				result.add(subSet); // ���Ӽ��洢����
			}
			return result;
		} else {
			return null;
		}
	}
 
	// �ж����������ཻ�Ƿ�Ϊ��
	public static boolean intersectionIsNull(ArrayList<String> l1,
			ArrayList<String> l2) {
		Set<String> s1 = new HashSet<String>(l1);
		Set<String> s2 = new HashSet<String>(l2);
 
		s1.retainAll(s2);
		if (s1.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
 
	/**
	 * �������յĹ����������ݹ�ʽ��������������¼�
	 */
	public static void connection() {
		for (ArrayList<String> key : L.keySet()) {// �����յĹ����������¼������ж�
			ArrayList<ArrayList<String>> key_allSubset = getSubset(key);
			// System.out.println(key_allSubset);
			for (int i = 0; i < key_allSubset.size(); i++) {
				ArrayList<String> item_pre = key_allSubset.get(i);
				if (0 < item_pre.size() && item_pre.size() < key.size()) {// ����ǿ����Ӽ�
					// �����ǿջ������Ӽ�֮���γɹ����¼�
					double item_pre_support = L_ALL.get(item_pre);
					for (int j = 0; j < key_allSubset.size(); j++) {
						ArrayList<String> item_post = key_allSubset.get(j);
						if (0 < item_post.size()
								&& item_post.size() < key.size()
								&& arrayListUnion(item_pre, item_post).equals(
										key)
								&& intersectionIsNull(item_pre, item_post)) {
							double item_post_support = L_ALL.get(item_post);// �������Ӽ���֧�ֶȱ������¼������Ŷ�
							double confident = item_pre_support
									/ item_post_support; // �¼������Ŷ�
							if (confident > min_confident) {// ����¼������Ŷȴ�����С���Ŷ�
								System.out
										.println(item_pre + "==>" + item_post);// ����һ�������¼�
								// System.out.println(item_pre_support + "==>" +
								// item_post_support);
							}
						}
 
					}
				}
			}
		}
	}
 
	public static void main(String[] args) {
 
		init();
		/*
		 * System.out.println(D); System.out.println(C); System.out.println(L);
		 * System.out.println("===");
		 */
		L = iteration(C, L);
		/*
		 * System.out.println(L); System.out.println(L_ALL);
		 * System.out.println("===");
		 */
		connection();
 
	}
}