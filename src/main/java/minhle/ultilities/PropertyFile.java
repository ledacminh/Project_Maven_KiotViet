package minhle.ultilities;

import minhle.commons.GlobalConstants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyFile {

    public static String getPropValue(String keyProp, String path) {
        FileInputStream fileInputStream = null;
        String value = "";
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream(GlobalConstants.FILE_DATA_PROPERTY_PATH);
            //Load properties file
            properties.load(fileInputStream);
            //get values from properties file
            value = properties.getProperty(keyProp);
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        } finally {
            if (fileInputStream != null ) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return value;
    }

    public static void setPropValue(String keyProp, String value, String path) throws RuntimeException {
        OutputStream outputStream = null;
        try {
            Properties properties = new Properties();
            outputStream = new FileOutputStream(GlobalConstants.FILE_DATA_PROPERTY_PATH);
            properties.setProperty(keyProp, value);
            //save the value
            properties.store(outputStream, "Set new value in properties file");
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(DataProperties.mapDataProperties.toString());
    }
}
