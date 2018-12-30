package org.sample.shop.db.queryrunner.statementfactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class StatementFactory {
    private static final String PATHNAME = StatementFactory.class.getResource("/sql.html").getPath();
    private static final String CHAR_SET = "UTF-8";

    private static Document doc;

    static {
        File input = new File(PATHNAME);
        try {
            doc = Jsoup.parse(input, CHAR_SET, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStatement(String statementId) {
        Element sql = doc.select(statementId).first();
        return sql.text();
    }
}
