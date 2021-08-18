package by.spetr.web.util;

public class TagRemover {
    private TagRemover() {}

    public static String doText(String text) {
        return text.replaceAll("\\<.*?\\>", "");
    }
}
