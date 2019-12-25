package top.aiteky.onebook.service;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.ibatis.annotations.Param;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.aiteky.onebook.entity.Book;
import top.aiteky.onebook.entity.BookUrl;
import top.aiteky.onebook.entity.SysErr;
import top.aiteky.onebook.entity.Tag;
import top.aiteky.onebook.mapper.*;
import top.aiteky.onebook.service.JsoupService;
import top.aiteky.onebook.utils.HeadersUtils;
import top.aiteky.onebook.utils.RegExpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JsoupService {

    @Autowired
    private SysErrMapper sysErrMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BookUrlMapper bookUrlMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookTagMapper bookTagMapper;


    /**
     * 将爬取到的书籍url存入数据库, 对于重复的url不做去重处理(url重复标签不重复)
     * cron
     */
    @Async
//    @Scheduled(cron = "0 0/10 * * * ? ")
    public void saveBoouUrls(){
        System.out.println("run...");
        if (tagMapper.count() > 0){
            for (Tag tag : tagMapper.selectByNotHasDone()){
                List<BookUrl> bookUrls = getBookUrls(tag.getLink(), tag.getName());
                for (BookUrl bookUrl : bookUrls){
                    bookUrlMapper.insert(bookUrl);
                }
                tagMapper.setTagHasDone(tag.getId());
            }
        }
    }

    /**
     * 将爬取到的书籍信息存入数据库, 对于重复的的书籍添加不同的标签)
     */
    @Async
//    @Scheduled(cron = "0 5/10 * * * ? ")
    public void saveBook() throws InterruptedException {
        List<BookUrl> bookUrls = bookUrlMapper.selectByNotHasDone();
        for (BookUrl bookUrl : bookUrls){
            String fid = RegExpUtils.findOne(bookUrl.getLink(), "[0-9]+");
            if (fid != null){
                Long id = Long.parseLong(fid);
                if (bookMapper.exist(id)){
                    Integer tid = tagMapper.findIdByName(bookUrl.getTag());
                    bookTagMapper.insert(id,tid);
                }else {
                    Book book = getBookInfo(bookUrl.getLink());
                    if (book != null){
                        book.setId(id);
                        bookMapper.insert(book);
                        Integer tid = tagMapper.findIdByName(bookUrl.getTag());
                        bookTagMapper.insert(id,tid);
                        bookUrlMapper.setBookUrlHasDone(bookUrl.getId());
                    }
                    Thread.sleep(5000);
                }
            }
        }
    }

    /**
     * 爬取标签对应的所有书籍的url, 自动爬取下一页, 豆瓣好像最多的只有50页, 即一个标签最多有1000条书籍链接
     * 书籍可能有多个标签, 所以链接会有重复(重复的链接标签不同), 此方法不错去重处理
     * @param url 标签对应的url
     * @param tag 标签
     * @return 爬取到的书籍url的集合
     */
    private List<BookUrl> getBookUrls(String url, String tag) {

        List<BookUrl> bookUrls = new ArrayList<>();
        String nextUrl = url;
        String base = "https://book.douban.com";
        while (true) {
            try {
                Document document = Jsoup.connect(nextUrl)
                        .headers(HeadersUtils.getHeaders())
                        .timeout(10000)
                        .get();
                Elements elements = document.select("#subject_list ul li.subject-item a.nbg");

                if (elements.size() == 0) break;
                for (Element element : elements) {
                    String href = element.attr("href");
                    bookUrls.add(new BookUrl(href, tag, false));
                }
                Elements next = document.select("span.next a");
                if (next.size() != 0) {
                    nextUrl = base + next.get(0).attr("href");
                } else break;
                Thread.sleep(3000);
            } catch (Exception e) {
                sysErrMapper.insert(new SysErr() {{
                    setDatetime(new Date());
                    setMessage(e.getMessage());
                    setClazz(e.getClass().toString());
                }});
            }
        }
        return bookUrls;
    }

    /**
     * 爬取相应url的书籍详情
     * @param url 书籍链接
     * @return Book
     */
    private Book getBookInfo(String url){
        try{
            Book book = new Book();

            Document document = Jsoup.connect(url)
                    .timeout(10000)
                    .headers(HeadersUtils.getHeaders())
                    .get();
            Elements vo = document.select("#wrapper");
            book.setTitle(vo.select("h1>span").first().text());
            Element info = vo.select("#info").first();

            Matcher mAuthor = Pattern.compile("<span.*?作者.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mAuthor.find()){
                book.setAuthor(mAuthor.group(0)
                        .replaceAll("<span.*?.*[\\s\\S].*<a.*?>", "")
                        .replaceAll("<.*?[\\s\\S]>", "")
                        .replaceAll("\n", "")
                        .trim());
            }

            Matcher mPress = Pattern.compile("<span.*?出版社:.*?</span>[\\s\\S]*?<.*?>").matcher(info.toString());
            if (mPress.find()){
                book.setPress(mPress.group(0)
                        .replaceAll("<span.*?出版社:.*?>", "")
                        .replaceAll("<.*?>", "")
                        .trim());
            }

            Matcher mOrigin = Pattern.compile("<span.*?原作名:.*?</span>[\\s\\S]*?<.*?>").matcher(info.toString());
            if (mOrigin.find()){
                book.setOriginal(mOrigin.group(0)
                        .replaceAll("<span.*?原作名:.*?>", "")
                        .replaceAll("<.*?>", "")
                        .trim());
            }

            Matcher mTranslate = Pattern.compile("<span.*?译者.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mTranslate.find()){
                book.setTranslator(mTranslate.group(0)
                        .replaceAll("<span.*?.*\">", "")
                        .replaceAll("<.*?>", "")
                        .trim());
            }

            Matcher mYear = Pattern.compile("<span.*?出版年.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mYear.find()){
                book.setImprint(mYear.group(0)
                        .replaceAll("[^0-9\\-].*?", "")
                        .trim());
            }

            Matcher mPage = Pattern.compile("<span.*?页数.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mPage.find()){
                Matcher m = Pattern.compile("[0-9]+").matcher(mPage.group(0));
                if (m.find())
                    book.setPages(Integer.parseInt(m.group(0)));
                else book.setPages(-1);
            }else book.setPages(-1);

            Matcher mPrice = Pattern.compile("<span.*?定价.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mPrice.find()){
                Matcher m = Pattern.compile("[0-9\\.]+").matcher(mPrice.group(0));
                if (m.find())
                    book.setPrice(Double.parseDouble(m.group(0)));
                else book.setPrice(0.0);
            }else book.setPrice(0.0);

            Matcher mBinding = Pattern.compile("<span.*?装帧.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mBinding.find()){
                book.setBinding(mBinding.group(0)
                        .replaceAll("<span.*?.*span>", "")
                        .replaceAll("<.*?>", "")
                        .trim());
            }

            Matcher mSeries = Pattern.compile("<span.*?丛书.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mSeries.find()){
                book.setSeries(mSeries.group(0)
                        .replaceAll("<span.*?[\\s\\S].*<a.*?>", "")
                        .replaceAll("<.*?>", "")
                        .trim());
            }

            Matcher mISBN = Pattern.compile("<span.*?ISBN.*?</span>[\\s\\S]*?<br.*?>").matcher(info.toString());
            if (mISBN.find()){
                book.setIsbn(mISBN.group(0)
                        .replaceAll("<span.*?.*span>", "")
                        .replaceAll("<.*?>", "")
                        .trim());
            }

            book.setScore(Double.parseDouble(vo.select("strong.rating_num").first().text()));
            book.setNumber(Long.parseLong(vo.select("a.rating_people span").first().text()));

            book.setImg(vo.select("#mainpic a").first().attr("href"));

            Elements report = vo.select("#link-report").first().select("div.intro p");
            StringBuffer content = new StringBuffer();
            report.forEach(it -> {
                content .append(it.text().trim() + "\n");
            });
            book.setContent(content.toString());
            return book;

        }catch (Exception e){
            sysErrMapper.insert(new SysErr() {{
                setDatetime(new Date());
                setMessage(e.getMessage());
                setClazz(e.getClass().toString());
            }});
            return null;
        }
    }


}
