package com.asd.common.csvtool;

/**
 * 用于人保的CSV文件的加密解密
 * Created by asd on 2017/7/7.
 */

import com.picc.constant.Constant;
import com.picc.constant.ThreadLocalConstant;
import com.picc.header.modle.DateHead;
import com.picc.header.tools.HeaderTool;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CSVTool
{
    public static void main(String[] args) throws IOException {
        //将明文的CSV文件修改为加密的
        //encodeCSV();
        //将加密的CSV文件进行解密
        decodeCSV();
    }


    private static List parseFile(File tempfile) {
        BufferedReader reader = null;
        ArrayList DateHeads = new ArrayList();
        try {
            if(tempfile.exists()) {
                reader = new BufferedReader(new FileReader(tempfile));
                reader.readLine();
                String temp;
                while((temp = reader.readLine()) != null) {
                    String[] head = temp.split(",");
                    if(head.length > 0) {
                        DateHead datehead = new DateHead();
                        if(!isNull(head[1])) {
                            datehead.setUser(head[1].replaceAll("\"", ""));
                            datehead.setSender(head[1].replaceAll("\"", ""));
                        }
                        if(!isNull(head[2])) {
                            datehead.setRequest_type(head[2].replaceAll("\"", ""));
                        }
                        if(!isNull(head[3])) {
                            datehead.setServer_version(head[3].replaceAll("\"", ""));
                        }
                        if(!isNull(head[4])) {
                            datehead.setPassword(head[4].replaceAll("\"", ""));
                        }
                        DateHeads.add(datehead);
                    }
                }
            }
        } catch (FileNotFoundException var17) {
            System.out.println("[" + HeaderTool.formatDate(new Date()) + "] [" + ThreadLocalConstant.getClientip() + "] [" + Constant.LOGREQUESTURL + "] [20] [10] [服务端CSV文件没找到]");
        } catch (IOException var18) {
            System.out.println("[" + HeaderTool.formatDate(new Date()) + "] [" + ThreadLocalConstant.getClientip() + "] [" + Constant.LOGREQUESTURL + "] [20] [10] [eris]");
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException var16) {
                var16.printStackTrace();
            }
        }
        return DateHeads;
    }

    private static boolean isNull(String str)
    {
        return (str == null) || ("".equals(str));
    }

    public static boolean fileDecrypt(File inFile, File outFile)
    {
        boolean flag = false;
        FileInputStream in = null;
        FileOutputStream out = null;
        try
        {
            in = new FileInputStream(inFile);
            out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1)
            {
                out.write(c ^ 0xFFFFFFFF);
            }
            flag = true;
        }
        catch (FileNotFoundException e) {
            System.out.println("[" + HeaderTool.formatDate(new Date()) + "] [" + ThreadLocalConstant.getClientip() + "] [" +
                    Constant.LOGREQUESTURL + "] [20] [10] [客户端CSV文件没找到]");
            try
            {
                if (in != null) {
                    in.close();
                }
                if (out != null)
                    out.close();
            }
            catch (IOException localIOException1)
            {
            }
        }
        catch (IOException e)
        {
            System.out.println("[" + HeaderTool.formatDate(new Date()) + "] [" + ThreadLocalConstant.getClientip() + "] [" +
                    Constant.LOGREQUESTURL + "] [20] [10] [esadsa]");
            try
            {
                if (in != null) {
                    in.close();
                }
                if (out != null)
                    out.close();
            }
            catch (IOException localIOException2)
            {
            }
        }
        finally
        {
            try
            {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException localIOException3)
            {
            }
        }

        return flag;
    }

    public static void encodeCSV(){



        //Boolean b = fileDecrypt(in,out);
        decodeCSV();
    }


    /**
     * 解密
     */
    private static void decodeCSV(){
        File in = new File("D:\\加密后的文件.csv");
        File out = new File("D:\\解密后的文件.csv");
        Boolean b = fileDecrypt(in,out);
        List l = parseFile(out);
//        for(int i = 0 ;i < l.size(); i++){
//            DateHead dead = (DateHead)l.get(i);
//            if("02050011".equals(dead.getRequest_type())){
//                System.out.println(dead.getServer_version() +" -- " +dead.getUser()+ " -- " + dead.getSender()+" -- " +dead.getRequest_type()+" -- "+dead.getPassword());
//            }
//
//        }
        // out.delete();
    }



}