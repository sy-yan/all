/**
 * Created by muziyongshixin on 2016/12/6.
 * 
 *
 * �����̰������������ļ�
 * fulldataΪ��ʦ����ԭʼ�����ļ���������������������ܲ����������û��ѡ�ý��в���
 * top1000data�Ǵ�fulldata��ժȡ��ǰ1000�����ݣ����������еĽ���ǻ�����ǰ1000�����ݽ��е�Ƶ����ھ�͹����ȷ���
 *
 */
package apriori;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Apriori�㷨ʵ�� ���ģʽ�ھ��漰��֧�ֶȣ���û�����Ŷȼ���
 *
 * AssociationRulesMining()����ʵ�����Ŷȼ���͹��������ھ�
 */
public class AprioriMyself {

    public static  int times=0;//��������
    private static  double MIN_SUPPROT = 0.02;//��С֧�ֶȰٷֱ�
    private static   double MIN_CONFIDENCE=0.6;//��С���Ŷ�
    private static boolean endTag = false;//ѭ��״̬��������ʶ
    static List<List<String>> record = new ArrayList<List<String>>();//���ݼ�
    static  List<List<String>> frequentItemset=new ArrayList<>();//�洢���е�Ƶ���
    static List<Mymap> map = new ArrayList();//���Ƶ����Ͷ�Ӧ��֧�ֶȼ���

    public static void main(String args[]){

        System.out.println("��������С֧�ֶȣ���0.05������С���Ŷȣ���0.6��");
        //Scanner in=new Scanner(System.in);
        MIN_SUPPROT=0.3;
        MIN_CONFIDENCE=0.7;


        /*************��ȡ���ݼ�**************/
        record = getRecord("src/apriori/1.txt");
        //����̨�����¼
        System.out.println("��ȡ���ݼ�record�ɹ�===================================");
        ShowData(record);


        Apriori();//����Apriori�㷨���Ƶ���
        System.out.println("Ƶ��ģʽ�ھ���ϡ�\n\n\n\n\n���й������ھ���С֧�ֶȰٷֱ�Ϊ��"+MIN_SUPPROT+"  ��С���Ŷ�Ϊ��"+MIN_CONFIDENCE);



         AssociationRulesMining();//�ھ��������
    }

    /**********************************************
     * ****************��ȡ����********************/
    public static List<List<String>> getRecord(String url) {
        List<List<String>> record = new ArrayList<List<String>>();
        try {
            String encoding = "UTF-8"; // �ַ�����(�ɽ�������������� )
            File file = new File(url);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT = null;
                while ((lineTXT = bufferedReader.readLine()) != null) {//��һ���ļ�
                    String[] lineString = lineTXT.split(" ");
                    List<String> lineList = new ArrayList<String>();
                    for (int i = 1; i < lineString.length; i++) {
                        lineList.add(lineString[i]);
                    }
                    record.add(lineList);
                }

                read.close();
            } else {
                System.out.println("�Ҳ���ָ�����ļ���");
            }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݲ�������");
            e.printStackTrace();
        }
        return record;
    }




    public static void Apriori()           /**ʵ��apriori�㷨**/
    {
        //************��ȡ��ѡ1�**************
        System.out.println("��һ��ɨ����1�� ��ѡ��CandidateItemset");
        List<List<String>> CandidateItemset = findFirstCandidate();
        ShowData(CandidateItemset);



        //************��ȡƵ��1�***************
        System.out.println("��һ��ɨ����1�� Ƶ����FrequentItemset");
        List<List<String>> FrequentItemset = getSupprotedItemset(CandidateItemset);
        AddToFrequenceItem(FrequentItemset);//��ӵ����е�Ƶ�����
        //����̨���1��Ƶ����
        ShowData(FrequentItemset);


         //*****************************��������**********************************
        times=2;
        while(endTag!=true){

            System.out.println("*******************************��"+times+"��ɨ���ѡ��");
            //**********���Ӳ���****��ȡ��ѡtimes�**************
            List<List<String>> nextCandidateItemset = getNextCandidate(FrequentItemset);
            //������еĺ�ѡ�
            ShowData(nextCandidateItemset);


            /**************��������***�ɺ�ѡk�ѡ���Ƶ��k�****************/
            System.out.println("*******************************��"+times+"��ɨ���Ƶ����");
            List<List<String>> nextFrequentItemset = getSupprotedItemset(nextCandidateItemset);
            AddToFrequenceItem(nextFrequentItemset);//��ӵ����е�Ƶ�����
            //������е�Ƶ���
            ShowData(nextFrequentItemset);


            //*********���ѭ��������������ģʽ**************
            if(endTag == true){
                System.out.println("\n\n\nApriori�㷨--->���Ƶ����==================================");
                ShowData(FrequentItemset);
            }
            //****************��һ��ѭ����ֵ********************
            FrequentItemset = nextFrequentItemset;
            times++;//����������һ
        }
    }



    public static void AssociationRulesMining()//���������ھ�
    {
        for(int i=0;i<frequentItemset.size();i++)
        {
            List<String> tem=frequentItemset.get(i);
            if(tem.size()>1) {
                List<String> temclone=new ArrayList<>(tem);
                List<List<String>> AllSubset = getSubSet(temclone);//�õ�Ƶ���tem�������Ӽ�
                for (int j = 0; j < AllSubset.size(); j++) {
                    List<String> s1 = AllSubset.get(j);
                    List<String> s2 = gets2set(tem, s1);
                    double conf = isAssociationRules(s1, s2, tem);
                    if (conf > 0)
                        System.out.println("���Ŷ�Ϊ��" + conf);
                }
            }

            }
        }


    public  static  double isAssociationRules(List<String> s1,List<String> s2,List<String> tem)//�ж��Ƿ�Ϊ��������
    {
        double confidence=0;
        int counts1;
        int countTem;
        if(s1.size()!=0&&s1!=null&&tem.size()!=0&&tem!=null)
        {
            counts1= getCount(s1);
            countTem=getCount(tem);
            confidence=countTem*1.0/counts1;

            if(confidence>=MIN_CONFIDENCE)
            {
                System.out.print("��������"+ s1.toString()+"=>>"+s2.toString()+"   ");
                return confidence;
            }
            else
                return 0;

        }
        else
            return 0;

    }

    public static int getCount(List<String> in)//����Ƶ����õ���֧�ֶȼ���
    {
        int rt=0;
        for(int i=0;i<map.size();i++)
        {
            Mymap tem=map.get(i);
            if(tem.isListEqual(in)) {
                rt = tem.getcount();
                return rt;
            }
        }
        return rt;

    }


    public static  List<String> gets2set(List<String> tem, List<String> s1)//����tem��ȥs1��ļ��ϼ�Ϊs2
    {
        List<String> result=new ArrayList<>();

        for(int i=0;i<tem.size();i++)//ȥ��s1�е�����Ԫ��
        {
            String t=tem.get(i);
            if(!s1.contains(t))
                result.add(t);
        }
        return  result;
    }


    public static List<List<String>> getSubSet(List<String> set){
        List<List<String>> result = new ArrayList<>(); //��������Ӽ��ļ��ϣ���{{},{1},{2},{1,2}}
        int length = set.size();
        int num = length==0 ? 0 : 1<<(length); //2��n�η���������setΪ�գ�numΪ0��������set��4��Ԫ�أ���ônumΪ16.

        //��0��2^n-1��[00...00]��[11...11]��
        for(int i = 1; i < num-1; i++){
            List<String> subSet = new ArrayList<>();

            int index = i;
            for(int j = 0; j < length; j++){
                if((index & 1) == 1){     //ÿ���ж�index���λ�Ƿ�Ϊ1��Ϊ1��Ѽ���set�ĵ�j��Ԫ�طŵ��Ӽ���
                    subSet.add(set.get(j));
                }
                index >>= 1;      //����һλ
            }

            result.add(subSet);       //���Ӽ��洢����
        }
        return result;
    }





    public  static  boolean  AddToFrequenceItem(List<List<String>> fre)
    {

        for(int i=0;i<fre.size();i++)
        {
            frequentItemset.add(fre.get(i));
        }
        return true;
    }



    public static  void ShowData(List<List<String>> CandidateItemset)//��ʾ��candidateitem�е����е��
    {
        for(int i=0;i<CandidateItemset.size();i++){
            List<String> list = new ArrayList<String>(CandidateItemset.get(i));
            for(int j=0;j<list.size();j++){
                System.out.print(list.get(j)+" ");
            }
            System.out.println();
        }
    }




    /**
     ******************************************************* �е�ǰƵ�������������һ�κ�ѡ��
     */
    private static List<List<String>> getNextCandidate(List<List<String>> FrequentItemset) {
        List<List<String>> nextCandidateItemset = new ArrayList<List<String>>();

        for (int i=0; i<FrequentItemset.size(); i++){
            HashSet<String> hsSet = new HashSet<String>();
            HashSet<String> hsSettemp = new HashSet<String>();
            for (int k=0; k< FrequentItemset.get(i).size(); k++)//���Ƶ������i��
                hsSet.add(FrequentItemset.get(i).get(k));
            int hsLength_before = hsSet.size();//���ǰ����
            hsSettemp=(HashSet<String>) hsSet.clone();
            for(int h=i+1; h<FrequentItemset.size(); h++){//Ƶ������i�����j��(j>i)����   ÿ����������һ��Ԫ�����    �µ�Ƶ�����ĳһ�У�
                hsSet=(HashSet<String>) hsSettemp.clone();//�����������ӵ�hasSet���ֲ���
                for(int j=0; j< FrequentItemset.get(h).size();j++)
                    hsSet.add(FrequentItemset.get(h).get(j));
                int hsLength_after = hsSet.size();
                if(hsLength_before+1 == hsLength_after && isnotHave(hsSet,nextCandidateItemset)){
                    //�������ȣ���ʾ�����1���µ�Ԫ��       ͬʱ�ж��䲻�Ǻ�ѡ�����Ѿ����ڵ�һ��
                    Iterator<String> itr = hsSet.iterator();
                    List<String>  tempList = new ArrayList<String>();
                    while(itr.hasNext()){
                        String Item = (String) itr.next();
                        tempList.add(Item);
                    }
                    nextCandidateItemset.add(tempList);
                }

            }

        }
        return nextCandidateItemset;
    }



    /**
     * �ж������Ԫ���γɵĺ�ѡ���Ƿ����µĺ�ѡ����
     */
    private static boolean isnotHave(HashSet<String> hsSet, List<List<String>> nextCandidateItemset) {//�ж�hsset�ǲ���candidateitemset�е�һ��
        List<String>  tempList = new ArrayList<String>();
        Iterator<String> itr = hsSet.iterator();
        while(itr.hasNext()){//��hssetת��ΪList<String>
            String Item = (String) itr.next();
            tempList.add(Item);
        }
        for(int i=0; i<nextCandidateItemset.size();i++)//����candidateitemset���������Ƿ��к�templist��ͬ��һ��
            if(tempList.equals(nextCandidateItemset.get(i)))
                return false;
        return true;
    }


    /**
     * ��k���ѡ����֦�õ�k��Ƶ����
     */
    private static List<List<String>> getSupprotedItemset(List<List<String>> CandidateItemset) { //�����е���Ʒ����֧�ֶȼ���
        // TODO Auto-generated method stub
        boolean end = true;
        List<List<String>> supportedItemset = new ArrayList<List<String>>();

        for (int i = 0; i < CandidateItemset.size(); i++){

            int count = countFrequent1(CandidateItemset.get(i));//ͳ�Ƽ�¼��

            if (count >= MIN_SUPPROT * (record.size()-1)){
                supportedItemset.add(CandidateItemset.get(i));
                map.add(new Mymap(CandidateItemset.get(i),count));//�洢��ǰƵ����Լ�����֧�ֶȼ���
                end = false;
            }
        }
        endTag = end;//����Ƶ����򲻻����
        if(endTag==true)
            System.out.println("*****************������֧�ֶȵ�"+times+"�,��������");
        return supportedItemset;
    }




    /**
     * ͳ��record�г���list���ϵĸ���
     */
    private static int countFrequent1(List<String> list) {//�����������ݼ�record���Ե�����ѡ������֧�ֶȼ���

        int count =0;
        for(int i=0;i<record.size();i++)//��record�ĵ�һ����ʼ����
        {
            boolean flag=true;
            for (int j=0;j<list.size();j++)//���record�еĵ�һ�����ݼ�����list�е�����Ԫ��
            {
                String t=list.get(j);
                if(!record.get(i).contains(t)) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                count++;//֧�ֶȼ�һ
        }

        return count;//����֧�ֶȼ���

    }

   
     //���һ���ѡ��
    private static List<List<String>> findFirstCandidate() {
        // TODO Auto-generated method stub
        List<List<String>> tableList = new ArrayList<List<String>>();
        HashSet<String> hs  = new HashSet<String>();//�½�һ��hash��������еĲ�ͬ��һά����

        for (int i = 1; i<record.size(); i++){  //�������е����ݼ����ҳ����еĲ�ͬ����Ʒ��ŵ�hs��
            for(int j=1;j<record.get(i).size();j++){
                hs.add(record.get(i).get(j));
            }
        }
        Iterator<String> itr = hs.iterator();
        while(itr.hasNext()){
            List<String>  tempList = new ArrayList<String>();
            String Item = (String) itr.next();
            tempList.add(Item);   //��ÿһ����Ʒ��ŵ�һ��List<String>��
            tableList.add(tempList);//���е�list<String>��ŵ�һ�����list��
        }
        return tableList;//�������е���Ʒ
    }
}
class  Mymap{//�Զ����map�࣬һ��������һ��Ƶ����Լ���֧�ֶȼ���
    public List<String> li=new LinkedList<>();
    public  int count;

    public Mymap(List<String> l,int c)//���캯��  �½�һ������
    {
        li=l;
        count=c;
    }

    public int getcount()//���صõ���ǰƵ�����֧�ֶȼ���
    {
        return count;
    }

    public boolean isListEqual(List<String> in)//�жϴ����Ƶ����Ƿ�ͱ�Ƶ�����ͬ
    {
        if(in.size()!=li.size())//���жϴ�С�Ƿ���ͬ
            return false;
        else {
            for(int i=0;i<in.size();i++)//���������Ƶ������ж��Ƿ�����Ԫ�ض������ڱ�Ƶ�����
            {
                if(!li.contains(in.get(i)))
                    return false;
            }
        }
        return true;//�������Ƶ�����С��ͬ��ͬʱ��Ƶ������������Ƶ���������Ԫ�أ����ʾ����Ƶ�������ȵģ�����Ϊ��
    }
}