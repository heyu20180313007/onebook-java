package top.aiteky.onebook.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtils {
    public static String findOne(String text, String reg){
        Matcher m = Pattern.compile(reg).matcher(text);
        if (m.find()){
            return m.group(0);
        }else return null;
    }
}
