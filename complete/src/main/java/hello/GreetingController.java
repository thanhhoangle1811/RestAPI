package hello;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Bookstore;
import model.BookStoreModel;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    @RequestMapping("/select.html")
    public Bookstore select(@RequestParam(value="id", defaultValue="0") int id) {
    	BookStoreModel model = new BookStoreModel();
        return model.find(id);
    }
    
    @RequestMapping("/insert.html")
    public Bookstore insert(@RequestParam(value="price") BigDecimal price,
    						@RequestParam(value="title") String title) {
    	BookStoreModel model = new BookStoreModel();
    	Bookstore bookstore = new Bookstore();
    	bookstore.setPrice(price);
    	bookstore.setTitle(title);
    	model.create(bookstore);
        return bookstore;
    }
}
