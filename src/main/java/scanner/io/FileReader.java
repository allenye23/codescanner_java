package scanner.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {

    public static String read(String filepath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(filepath));
        byte[] buf = new byte[inputStream.available()];
        inputStream.read(buf);
        String str=new String(buf,"UTF-8");
        inputStream.close();
        return str;
    }

    public static String getModuleName(String filePath){
        Pattern pattern = Pattern.compile("WLS_(PRS|DOM)_[(A-Za-z)]+");
        Matcher matcher = pattern.matcher(filePath);
        if (matcher.find()){
           return matcher.group();
        }
        return "";
    }

    public static String getDomainName(String filePath){
        Pattern pattern = Pattern.compile("iris4_[(A-Za-z)]+");
        Matcher matcher = pattern.matcher(filePath);
        if (matcher.find()){
           return  matcher.group().substring(6);
        }
        return "";
    }
}
