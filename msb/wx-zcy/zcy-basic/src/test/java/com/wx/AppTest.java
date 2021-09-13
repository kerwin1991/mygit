package com.wx;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import com.wx.dto.Langs;
import com.wx.dto.SelectTreeNode;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void parseFile() {


        TreeMap<String, SelectTreeNode> nodeMap = new TreeMap<>();


        TreeMap<String, Langs> codeLangsMap = new TreeMap<>();


/*        English | Simplified Chinese | Spanish | Russian | Thai | Vietnamese | Indonesian | Turkish | Korean | Portuguese | Traditional Chinese
        OKEx Reject Responses*/



//        String fileName = "/Users/oker/Desktop/reason.txt";
//        String fileName = "/Users/oker/Desktop/text_ex.txt";
        String fileName = "/Users/oker/Desktop/text_ex2.txt";
        String line = "";
        int empthyline = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {

                if (empthyline > 1) {
                    break;
                }
                if (line.length() == 0) {
                    line = in.readLine();
                    empthyline++;
                    continue;
                }
                empthyline = 0;

//                System.out.println(line);

                String[] split = line.split("\\|");
                if (!nodeMap.containsKey(split[1])) {
                    SelectTreeNode treeNode = new SelectTreeNode();
                    treeNode.setCode(split[1].substring(0, 1));
                    // 中文
//                    treeNode.setDesc(split[1] + ":" + split[3]);
                    // 英文
                    treeNode.setDesc(split[0] + ":" + split[2]);

                    treeNode.setChilds(new LinkedList<SelectTreeNode>());
                    nodeMap.put(split[1], treeNode);
                }
                SelectTreeNode parentNode = nodeMap.get(split[1]);
                SelectTreeNode c = new SelectTreeNode();
                c.setCode(split[4]);
                // 中文
//                c.setDesc("(" + c.getCode() + ")" + split[6]);
                // 英文
                c.setDesc("(" + c.getCode() + ")" + split[5]);
                parentNode.getChilds().add(c);

                // 用户多语言
                Langs langs = new Langs();
                langs.setEn_us(split[7]); // 用户 英文
                langs.setZh_cn(split[8]); // 用户 中文
                langs.setEs_es(split[9]); // 用户
                langs.setRu_ru(split[10]); // 用户

                langs.setTh_th(split[11]); // 用户

                langs.setVi_vn(split[12]); // 用户

                langs.setIn_id(split[13]); // 用户

                langs.setTr_tr(split[14]); // 用户

                langs.setKo_kr(split[15]); // 用户

                langs.setPt_br(split[16]); // 用户


                langs.setFr_fr(split[17]); // 用户
                langs.setDe_de(split[18]); // 用户
                langs.setIt_it(split[19]); // 用户
                langs.setPl_pl(split[20]); // 用户




                langs.setZh_tw(split[21]); // 用户

                codeLangsMap.put(split[4], langs);
                // 用户多语言

//                System.out.println(line);

                line = in.readLine();

            }
            in.close();

            System.out.println(JSONObject.toJSON(nodeMap.values()));
            System.out.println(JSONObject.toJSON(codeLangsMap));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void parseFileForCoin() {


        TreeMap<String, SelectTreeNode> nodeMap = new TreeMap<>();

        TreeMap<String, Langs> codeLangsMap = new TreeMap<>();

/*        English | Simplified Chinese |
                Okcoin Reject Responses*/

//        String fileName = "/Users/oker/Desktop/reason.txt";
//        String fileName = "/Users/oker/Desktop/test_com.txt";
        String fileName = "/Users/oker/Desktop/test_com2.txt";
        String line = "";
        int empthyline = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {
                if (empthyline > 1) {
                    break;
                }
                if (line.length() == 0) {
                    line = in.readLine();
                    empthyline++;
                    continue;
                }
                empthyline = 0;
                String[] split = line.split("\\|");
                if (!nodeMap.containsKey(split[1])) {
                    SelectTreeNode treeNode = new SelectTreeNode();
                    treeNode.setCode(split[1].substring(0, 1));
                    // 中文
                    treeNode.setDesc(split[1] + ":" + split[3]);
                    // 英文
//                    treeNode.setDesc(split[0] + ":" + split[2]);

                    treeNode.setChilds(new LinkedList<SelectTreeNode>());
                    nodeMap.put(split[1], treeNode);
                }
                SelectTreeNode parentNode = nodeMap.get(split[1]);
                SelectTreeNode c = new SelectTreeNode();
                c.setCode(split[4]);
                // 中文
                c.setDesc("(" + c.getCode() + ")" + split[6]);
                // 英文
//                c.setDesc("(" + c.getCode() + ")" + split[5]);
                parentNode.getChilds().add(c);

                // 用户多语言
                Langs langs = new Langs();
                langs.setEn_us(split[7]); // 用户 英文
                try {

                    langs.setZh_cn(split[8]); // 用户 中文
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(line);
                }

                codeLangsMap.put(split[4], langs);
                // 用户多语言

//                System.out.println(line);

                line = in.readLine();

            }
            in.close();

            System.out.println(JSONObject.toJSON(nodeMap.values()));
            System.out.println(JSONObject.toJSON(codeLangsMap));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void sql_1_okex() {
        String format = "INSERT INTO kyc_expression_relation (id, code, chiness_desc, common_desc) VALUES (1,'%s','%s', '%s');";


        Set<String> codes = new HashSet<>();


        TreeMap<String, Langs> codeLangsMap = new TreeMap<>();


/*        English | Simplified Chinese | Spanish | Russian | Thai | Vietnamese | Indonesian | Turkish | Korean | Portuguese | Traditional Chinese
        OKEx Reject Responses*/


//        String fileName = "/Users/oker/Desktop/text_ex2.txt";
        String fileName = "/Users/oker/Desktop/test_com2.txt";
        String line = "";
        int empthyline = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {

                if (empthyline > 1) {
                    break;
                }
                if (line.length() == 0) {
                    line = in.readLine();
                    empthyline++;
                    continue;
                }
                empthyline = 0;

//                System.out.println(line);

                String[] split = line.split("\\|");

                if (codes.contains(split[1])) {
                    line = in.readLine();
                    continue;

                }
                codes.add(split[1]);

                System.out.println(String.format(format, split[1].substring(0,1), split[3], split[2]));

                line = in.readLine();

            }
            in.close();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    @Test
    public void sql_2_okex() {

        Map<String, Integer> codeMap = new HashMap<>();
        codeMap.put("A", 1);
        codeMap.put("B", 2);
        codeMap.put("C", 3);
        codeMap.put("D", 4);
        codeMap.put("E", 5);
        codeMap.put("F", 6);
        codeMap.put("G", 7);
        codeMap.put("J", 10);
        codeMap.put("K", 11);
        codeMap.put("L", 12);
        codeMap.put("Z", 26);


        codeMap.put("H", 8);
        codeMap.put("I", 9);
        codeMap.put("M", 13);




        String format = "INSERT INTO kyc_expression_relation (code, chiness_desc, common_desc, p_id) VALUES ('%s','%s', '%s', %s);";




/*        English | Simplified Chinese | Spanish | Russian | Thai | Vietnamese | Indonesian | Turkish | Korean | Portuguese | Traditional Chinese
        OKEx Reject Responses*/


//        String fileName = "/Users/oker/Desktop/text_ex2.txt";
        String fileName = "/Users/oker/Desktop/test_com2.txt";

        String line = "";
        int empthyline = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {

                if (empthyline > 1) {
                    break;
                }
                if (line.length() == 0) {
                    line = in.readLine();
                    empthyline++;
                    continue;
                }
                empthyline = 0;

//                System.out.println(line);

                String[] split = line.split("\\|");


                System.out.println(String.format(format, split[4], split[6], split[5], codeMap.get(split[4].substring(0,1))));

                line = in.readLine();

            }
            in.close();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    @Test
    public void sql_3_okex() {

        Map<Integer, Integer> idxTypeMap = new HashMap<>();
        idxTypeMap.put(7, 1);
        idxTypeMap.put(8, 2);


     /*   idxTypeMap.put(9, 12);
        idxTypeMap.put(10, 7);
        idxTypeMap.put(11, 8);
        idxTypeMap.put(12, 10);
        idxTypeMap.put(13, 4);
        idxTypeMap.put(14, 9);
        idxTypeMap.put(15, 5);
        idxTypeMap.put(16, 6);
        idxTypeMap.put(17, 3);
        idxTypeMap.put(18, 13);
        idxTypeMap.put(19, 14);
        idxTypeMap.put(20, 15);
        idxTypeMap.put(21, 11);*/


/*        langs.setEn_us(split[7]); // 用户 英文
        langs.setZh_cn(split[8]); // 用户 中文
        langs.setEs_es(split[9]); // 用户
        langs.setRu_ru(split[10]); // 用户

        langs.setTh_th(split[11]); // 用户

        langs.setVi_vn(split[12]); // 用户

        langs.setIn_id(split[13]); // 用户

        langs.setTr_tr(split[14]); // 用户

        langs.setKo_kr(split[15]); // 用户

        langs.setPt_br(split[16]); // 用户


        langs.setFr_fr(split[17]); // 用户
        langs.setDe_de(split[18]); // 用户
        langs.setIt_it(split[19]); // 用户
        langs.setPl_pl(split[20]); // 用户
        langs.setZh_tw(split[21]); // 用户

        */



        String format = "INSERT INTO kyc_expression_multi_lang (code, lang_type, description) VALUES ('%s',%s, \"%s\");";




/*        English | Simplified Chinese | Spanish | Russian | Thai | Vietnamese | Indonesian | Turkish | Korean | Portuguese | Traditional Chinese
        OKEx Reject Responses*/


//        String fileName = "/Users/oker/Desktop/text_ex2.txt";
        String fileName = "/Users/oker/Desktop/test_com2.txt";


        String line = "";
        int empthyline = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {

                if (empthyline > 1) {
                    break;
                }
                if (line.length() == 0) {
                    line = in.readLine();
                    empthyline++;
                    continue;
                }
                empthyline = 0;

//                System.out.println(line);

                String[] split = line.split("\\|");

                for (Integer key : idxTypeMap.keySet()) {

                    System.out.println(String.format(format, split[4], idxTypeMap.get(key), split[key]));

                }



                line = in.readLine();

            }
            in.close();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Test
    public void approve() {

        Map<Integer, Integer> idxTypeMap = new HashMap<>();
        idxTypeMap.put(7, 1);
        idxTypeMap.put(8, 2);


        idxTypeMap.put(9, 12);
        idxTypeMap.put(10, 7);
        idxTypeMap.put(11, 8);
        idxTypeMap.put(12, 10);
        idxTypeMap.put(13, 4);
        idxTypeMap.put(14, 9);
        idxTypeMap.put(15, 5);
        idxTypeMap.put(16, 6);
        idxTypeMap.put(17, 3);
        idxTypeMap.put(18, 13);
        idxTypeMap.put(19, 14);
        idxTypeMap.put(20, 15);
        idxTypeMap.put(21, 11);



        String format = "INSERT INTO kyc_expression_multi_lang (code, lang_type, description) VALUES ('%s',%s, \"%s\");";




/*       English | Simplified Chinese | Spanish | Russian | Thai | Vietnamese | Indonesian | Turkish | Korean | Portuguese | French
| German | Italian | Polish | Traditional Chinese

        */


        String fileName = "/Users/oker/Desktop/pass_expression.txt";


        String line = "";
        int empthyline = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {

                if (empthyline > 1) {
                    break;
                }
                if (line.length() == 0) {
                    line = in.readLine();
                    empthyline++;
                    continue;
                }
                empthyline = 0;

//                System.out.println(line);

                String[] split = line.split("\\|");

                for (Integer key : idxTypeMap.keySet()) {

                    System.out.println(String.format(format, split[4], idxTypeMap.get(key), split[key]));

                }



                line = in.readLine();

            }
            in.close();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
