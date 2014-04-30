package com.hubhead.utilities.sort.orders;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.hubhead.utilities.sort.utils.Configurations;
import com.hubhead.utilities.sort.utils.OrderTypes;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by salman on 2014-04-27.
 */
public abstract class SortOrder {

    private OrderTypes orderType;

    private Configurations configurations;

    public SortOrder(OrderTypes orderType, Configurations configurations) {
        this.orderType = orderType;
        this.configurations = configurations;
    }

    protected String[] readFile() {

        System.out.println("Start SortOrder.readFile()");

        LineIterator iterator = null;
        String[] words = null;

        try {
            File sourceFile = new File(configurations.getSourceFile());
            iterator = FileUtils.lineIterator(sourceFile, configurations.getFileEncoding());
            ArrayList<String> list = new ArrayList<String>(1000);
            while (iterator.hasNext()) {
                Iterable<String> wordsSplit = Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().split(iterator.nextLine());
                for (String s : wordsSplit) {
                    list.add(s);
                }
            }
            words = list.toArray(new String[list.size()]);
            // free memory
            list = null;
        } catch (IOException ex) {
            System.out.println("Could not read source file " + configurations.getSourceFile() + ". Exiting!!");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (iterator != null) {
                try {
                    LineIterator.closeQuietly(iterator);;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }

        System.out.println("End SortOrder.readFile()");

        return words;
    }

    protected void writeToFile(String[] words) {

        System.out.println("Start SortOrder.writeToFile()");
        OutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;

        try {
            outputStream = new FileOutputStream(configurations.getTargetFile());
            int buffSize = 32 * 1024;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, configurations.getFileEncoding()), buffSize);
            for(String word : words) {
                bufferedWriter.write(word);
                bufferedWriter.newLine();
            }
            // free memory
            words = null;
        } catch (IOException ex) {
            System.out.println("Could not write to target file " + configurations.getTargetFile() + ". Exiting!!");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (outputStream != null && bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                    outputStream.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }

        System.out.println("End SortOrder.writeToFile()");
    }

    public abstract void process();
    public Configurations getConfigurations() {
        return configurations;
    }
}

