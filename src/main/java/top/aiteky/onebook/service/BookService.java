package top.aiteky.onebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.aiteky.onebook.entity.Book;
import top.aiteky.onebook.mapper.BookMapper;
import top.aiteky.onebook.model.Result;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public Result oneBook() {
        Book book = bookMapper.oneBook();
        try {
            if (!new ClassPathResource("static/img/" + book.getId() + ".jpg").exists()){
                downloadBookImg(book.getImg(), book.getId().toString());
            }
            book.setImg("/img/" + book.getId() + ".jpg");
        }catch (Exception ignored){}

        return new Result(){{
           setCode(200);
           setMsg("success");
           setData(new HashMap(){{
               put("book", book);
           }});
        }};
    }

    public Result findAllBooksLimit(Long start, Long num) {
        List<Book> books = bookMapper.findAllBooksLimit(start, num);
        try {
            books.forEach(book->{
                if (!new ClassPathResource("static/img/" + book.getId() + ".jpg").exists()){
                    downloadBookImg(book.getImg(), book.getId().toString());
                }
                book.setImg("/img/" + book.getId() + ".jpg");
            });
        }catch (Exception ignored){}

        return new Result(){{
            setCode(200);
            setMsg("success");
            setData(new HashMap(){{
                put("books", books);
            }});
        }};
    }

    public Result search(String key) {
        List<Book> books = bookMapper.findBookLikeKeywords("%" + key + "%");
        try {
            books.forEach(book->{
                if (!new ClassPathResource("static/img/" + book.getId() + ".jpg").exists()){
                    downloadBookImg(book.getImg(), book.getId().toString());
                }
                book.setImg("/img/" + book.getId() + ".jpg");
            });
        }catch (Exception ignored){}

        return new Result(){{
            setCode(200);
            setMsg("success");
            setData(new HashMap(){{
                put("books", books);
            }});
        }};
    }

    @Async
    public void downloadBookImg(String url, String name){
        try{
            URL oUrl = new URL(url);
            BufferedImage img = ImageIO.read(oUrl);
            ImageIO.write(img, "jpg", new File(new ClassPathResource("static/img").getFile().getPath() + "/" +  name + ".jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
