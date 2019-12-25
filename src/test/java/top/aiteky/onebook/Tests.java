package top.aiteky.onebook;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import top.aiteky.onebook.entity.Book;
import top.aiteky.onebook.entity.BookUrl;
import top.aiteky.onebook.utils.HeadersUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tests {

    @Test
    public void testForeachExit(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
//        list.forEach(it -> {
//            System.out.println(it);
//            if (it == 5) return;
//        });
        for (int it : list){
            System.out.println(it);
            if (it == 5) return;
        }
    }

    @Test
    public void testAssert(){
        Book book = null;
        System.out.println("start assert");
        assert book != null;
        System.out.println("end assert");
    }

    @Test
    public void testLushi() throws IOException {

//        String baseUrl = "https://hs.fbigame.com/";
        //https://hs.fbigame.com/ajax.php?
        // mod=get_cards_list
        // &mode=-1
        // &extend=-1
        // &mutil_extend=
        // &hero=-1
        // &rarity=-1
        // &cost=-1
        // &mutil_cost=
        // &type=-1
        // &collectible=-1
        // &page=1
        // &search=
        // &deckmode=normal
        // &hash=3b32f3b5
        String apiUrl = "https://hs.fbigame.com/ajax.php?mod=get_cards_list&mode=-1&extend=-1&mutil_extend=&hero=-1&rarity=-1&cost=-1&mutil_cost=&type=-1&collectible=-1&page=4&search=&deckmode=normal&hash=3b32f3b5";

        Connection.Response execute = Jsoup.connect(apiUrl).execute();
        System.out.println(execute);


    }


    @Test
    public void testBookUrl() throws IOException {

        List<BookUrl> bookUrls = new ArrayList<>();
        String nextUrl = "https://book.douban.com/tag/%E5%B0%8F%E8%AF%B4";
        Document document = Jsoup.connect(nextUrl)
//                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.56 Safari/537.36 Edg/79.0.309.40")
//                        .header("cookie", "bid=DC5khpS4LE0; douban-fav-remind=1; gr_user_id=08aec6d9-57f6-4636-884b-a93999dc160a; _vwo_uuid_v2=DB431D524FE08F4B30D8C133C7345C16C|9689c97a3137bd5aea46da51d447743c; viewed=\"1477390_4822685_26829016_1770782_25862578_26642866\"; push_noty_num=0; push_doumail_num=0; __gads=ID=49d3841b4fb07c00:T=1575563166:S=ALNI_MbdaTk2mpCgrNl44jaHTDIFwueRGA; ll=\"118188\"; __utmz=30149280.1576576631.36.8.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/passport/login; __utma=30149280.1906114953.1574947812.1576576631.1576836358.37; __utmc=30149280; __utmt=1; dbcl2=\"208268304:shuPdZnG/So\"; ck=NdBx; __utmv=30149280.20826; __utmb=30149280.5.10.1576836358")
                .headers(HeadersUtils.getHeaders())
                .timeout(10000)
                .get();
        Elements elements = document.select("#subject_list ul li.subject-item a.nbg");

        if (elements.size() == 0) return ;
        for (Element element : elements) {
            String href = element.attr("href");
            System.out.println(href);
        }
    }
    @Test
    public void testBookInfo() throws IOException {

        Document document = Jsoup.connect("https://book.douban.com/subject/25862578/")
                .timeout(10000)
                .headers(HeadersUtils.getHeaders())
                .get();
        System.out.println(document);
    }
}
