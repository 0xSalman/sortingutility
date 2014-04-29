package com.hubhead.utilities;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

/**
 * Test sorting utility
 *
 */
public class App  {

    public static void main( String[] args ) throws IOException {

       /* SortFactory sortFactory = new SortFactory();
        sortFactory.process();*/

/*        InputStream inputStream = new FileInputStream("/Users/salman/Development/words.txt");
        File file = new File("/Users/salman/Development/words.txt");
        Iterable<String> result = Splitter.on(CharMatcher.WHITESPACE).trimResults().omitEmptyStrings()
                        .split(Files.asCharSource(file, Charset.forName("UTF-8")).read());


        for(String s: result) {
            System.out.println(s);
        }*/

        LineIterator iterator = FileUtils.lineIterator(new File("/Users/salman/Development/words.txt"), "UTF-8");
        while (iterator.hasNext()) {
            String line = iterator.nextLine();
            Iterable<String> result = Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().split(line);
            for(String s: result) {
                System.out.println(s);
            }
        }
    }
}
