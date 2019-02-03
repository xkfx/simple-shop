package org.sample.shop.common.db.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.sample.shop.common.annotation.ThreadSafe;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MetadataBuffer {
    private static final String RESOURCE_ROOT = File.separator + "sqls" + File.separator;
    private static final String CHAR_SET = "UTF-8";

    private static Map<String, Metadata> map = new HashMap<>(); // TODO 线程安全

    private static String metadataId2rsName(String metadataId) {
        final String resourceName = metadataId.substring(0, metadataId.indexOf('_')) + ".html";
        return RESOURCE_ROOT + resourceName;
    }

    private static void html2buffer(String pathname) throws IOException {
        final Document doc = Jsoup.parse(MetadataBuffer.class.getClassLoader().getResourceAsStream(pathname), CHAR_SET, "");
        org.jsoup.select.Elements elements = doc.select("sql");
        for (Element x : elements) {
            map.put(x.id(), new Metadata(x.text(), x.attr("type")));
        }
    }

    public static Metadata getMetadata(String metadataId) throws IOException {
        Metadata result = map.get(metadataId);
        if (result != null) {
            return result;
        } else {
            html2buffer(metadataId2rsName(metadataId));
            result = map.get(metadataId);
            if (result != null) {
                return result;
            } else {
                throw new IOException("Invalid metadataId " + metadataId);
            }
        }
    }
}
