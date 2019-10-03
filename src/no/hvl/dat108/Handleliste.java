package no.hvl.dat108;

import java.util.ArrayList;
import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

public class Handleliste extends ArrayList<String> {

    public String convertToHtml(){
        StringBuilder sb = new StringBuilder();

        for (String s : this) {
            String str = escapeHtml4(s);
            sb.append("<form action=\"handleliste\" method=\"post\">\n");
            sb.append("    <input type=\"hidden\" name=\"delete\" value=\"" + str + "\">\n");
            sb.append("    <p><input type=\"submit\" value=\"Slett\"> " + str + "</p>\n");
            sb.append("</form>\n");
        }
        return sb.toString();
    }
}
