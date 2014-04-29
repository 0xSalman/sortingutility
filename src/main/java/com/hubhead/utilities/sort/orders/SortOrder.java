package com.hubhead.utilities.sort.orders;

import com.hubhead.utilities.sort.utils.OrderTypes;

import java.io.*;

/**
 * Created by salman on 2014-04-27.
 */
public abstract class SortOrder {

    private OrderTypes orderType;

    public SortOrder(OrderTypes orderType) {
        this.orderType = orderType;
    }

    protected String[] readFile(String sourceFile, String encoding) {

        System.out.println("Start SortOrder.readFile()");

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String[] list = null;

        try {
            inputStream = new FileInputStream(sourceFile);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));
            /*String wholeFile = IOUtils.toString(inputStream, Charset.forName(encoding));
            list = wholeFile.split("\\s+");*/
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find source file " + sourceFile);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }

        }

        System.out.println("End SortOrder.readFile()");

        return list;
    }

    protected void writeToFile(String targetFile, String[] list, String encoding) {

        System.out.println("Start SortOrder.writeToFile()");

        try {

//            IOUtils.writeLi

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        System.out.println("End SortOrder.writeToFile()");
    }

    public abstract void process();

}

