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
    private static final String RESOURCE_ROOT = "/sqls/";
    private static final String CHAR_SET = "UTF-8";

    /**
     * TODO map加载完之后就维持不变了，不同步是否可行呢？
     */
    private static Map<String, Metadata> map = new HashMap<>();

    private static String metadataId2Pathname(String metadataId) {
        final String resourceName = metadataId.substring(0, metadataId.indexOf('_')) + ".html";
        return MetadataBuffer.class.getResource(RESOURCE_ROOT + resourceName).getPath();
    }
    private static void html2buffer(String pathname) throws IOException {
        final Document doc = Jsoup.parse(new File(pathname), CHAR_SET, "");
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
            html2buffer(metadataId2Pathname(metadataId));
            result = map.get(metadataId);
            if (result != null) {
                return result;
            } else {
                throw new IOException("Invalid id");
            }
        }
    }

    public static void main(String[] args) {
        Metadata metadata = null;
        try {
            metadata = getMetadata("cart_getPreOrder");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(metadata.getSql());
        System.out.println(map);
    }
}
