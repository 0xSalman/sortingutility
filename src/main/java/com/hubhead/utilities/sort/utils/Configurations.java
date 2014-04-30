package com.hubhead.utilities.sort.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * Created by salman on 2014-04-27.
 */
public class Configurations {

    private final String PROP_NAME = "config.properties";
    private Properties props;
    private String sourceFile;
    private String targetFile;
    private String fileEncoding;
    private String orderTypeStr;
    private String algorithmTypeStr;
    private AlgorithmTypes algorithmType;
    private OrderTypes orderType;
    private String sortingLangInit;
    private Locale sortingLangLocal;

    /**
     * Default constructor - use it when loading configurations from an external property file
     */
    public Configurations() {

    }

    /**
     * Second constructor - use it when not using an external property file
     * give mandatory variables.
     * OrderTypes.LEXICAL is used to sort words
     *
     * @param sourceFile - file containing unsorted words
     * @param targetFile - result file containing sorted words
     * @param fileEncoding - source/target files character encoding
     */
    public Configurations(String sourceFile, String targetFile, String fileEncoding) {
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
        this.fileEncoding = fileEncoding;
        this.orderType = OrderTypes.LEXICAL;
    }

    /**
     * Third constructor - use it when not using an external property file
     * and specific OrderType and AlgorithmType
     *
     * @param sourceFile
     * @param targetFile
     * @param fileEncoding
     * @param orderType - sorting order
     * @param algorithmType - sorting algorithm
     * @param sortingLangInit - required if using language specific OrderType (Collator)
     */
    public Configurations(String sourceFile, String targetFile, String fileEncoding,
                          OrderTypes orderType, AlgorithmTypes algorithmType, String sortingLangInit) {
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
        this.fileEncoding = fileEncoding;
        this.orderType = orderType;
        this.algorithmType = algorithmType;
        this.sortingLangInit = sortingLangInit;
    }

    /**
     * Initialize configuration object from external properties file
     * in the class path
     */
    public void initFromDefaultPropFile() {

        props = new Properties();

        try {
            props.load(new FileInputStream(PROP_NAME));
            loadConfigurations();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find property file " + PROP_NAME);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Initialize configuration object from given external properties file
     *
     * @param filePath - absolute path to properties file
     */

    public void initFromCustomPropFile(String filePath) {

        props = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(filePath);
            props.load(inputStream);
            loadConfigurations();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find property file " + filePath);
            ex.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Load configurations from an external property file
     *
     */
    private void loadConfigurations() {

        try {
            sourceFile = props.getProperty(ConfigConstants.SOURCE_FILE).trim();
            targetFile = props.getProperty(ConfigConstants.TARGET_FILE).trim();
            fileEncoding = props.getProperty(ConfigConstants.FILE_ENCODING).trim().toUpperCase();
            sortingLangInit = props.getProperty(ConfigConstants.SORTING_ORDER_LANGUAGE).trim();
            orderTypeStr = props.getProperty(ConfigConstants.SORTING_ORDER).trim().toUpperCase();
            algorithmTypeStr = props.getProperty(ConfigConstants.SORTING_ALGORITHM).trim().toUpperCase();
        } catch (Exception ex) {
            System.out.println("Failed while loading configurations from properties file");
            ex.printStackTrace();
        }
    }

    /** Check whether configuration is valid
     *
     * @return true if valid false otherwise
     */
    public boolean validateConfigurations() {

        try {

            if (nullOrEmpty(sourceFile) || nullOrEmpty(targetFile)) {
                System.out.println("Missing mandatory information: source or target file");
                return false;
            }
            if (nullOrEmpty(fileEncoding) || !isEncodingSupported()) {
                System.out.println("File encoding is not missing or not supported");
            }
            if (orderType == null) {
                if (nullOrEmpty(orderTypeStr)) {
                    orderType = OrderTypes.LEXICAL;
                } else {
                    if (OrderTypes.contains(orderTypeStr)) {
                        orderType = Enum.valueOf(OrderTypes.class, orderTypeStr);
                    } else {
                        // this behaviour can be changed to return false
                        orderType = OrderTypes.LEXICAL;
                    }

                    if (orderType == OrderTypes.SPECIFIC) {
                        return validateOrderingLanguage();
                    }

                    if (orderType != OrderTypes.LEXICAL) {
                        if (AlgorithmTypes.contains(algorithmTypeStr)) {
                            algorithmType = Enum.valueOf(AlgorithmTypes.class, algorithmTypeStr);
                        } else {
                            // user merge sort as a default choice
                            algorithmType = AlgorithmTypes.MERGE;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Failed while validating configuration parameters");
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean nullOrEmpty(String str) {
        return (str == null || str.isEmpty()) ? true : false;
    }

    private boolean isEncodingSupported() {
        return Charset.availableCharsets().keySet().contains(fileEncoding);
    }

    private boolean validateOrderingLanguage() {

        Map<String, Locale> langsLocal = new HashMap<String, Locale>();
        langsLocal.put("en", Locale.ENGLISH);
        langsLocal.put("fr", Locale.FRENCH);
        langsLocal.put("de", Locale.GERMAN);
        langsLocal.put("ja", Locale.JAPANESE);
        langsLocal.put("ko", Locale.KOREAN);
        langsLocal.put("zh", Locale.CHINESE);
        langsLocal.put("it", Locale.ITALIAN);

        if (nullOrEmpty(sortingLangInit)) {
            System.out.println("Missing sorting language initials");
            return false;
        }
        if (langsLocal.containsKey(sortingLangInit)) {
            sortingLangLocal = langsLocal.get(sortingLangInit);
            return true;
        } else {
            System.out.println(sortingLangInit + " is invalid or not supported initial for language");
            return false;
        }
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public AlgorithmTypes getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(AlgorithmTypes algorithmType) {
        this.algorithmType = algorithmType;
    }

    public OrderTypes getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypes orderType) {
        this.orderType = orderType;
    }

    public Locale getSortingLangLocal() {
        return sortingLangLocal;
    }

    public void setSortingLangLocal(Locale sortingOrderLanguage) {
        this.sortingLangLocal = sortingOrderLanguage;
    }

    public String getSortingLangInit() {
        return sortingLangInit;
    }

    public void setSortingLangInit(String sortingLangInit) {
        this.sortingLangInit = sortingLangInit;
    }
}
