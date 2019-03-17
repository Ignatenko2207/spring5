package it.discovery.loader;

import it.discovery.config.AppConfig;
import it.discovery.model.Book;
import it.discovery.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class SpringStarter {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext()) {

            context.getEnvironment().setActiveProfiles("dev", "test");
            context.register(AppConfig.class);
            context.refresh();

            BookService service = context.getBean(BookService.class);
            //service = context.getBean("service");

            Book book = new Book();
            book.setName("Introduction into Spring");
            book.setPages(100);
            book.setYear(2016);
            service.saveBook(book);

            var future = service.findBooks();
            future.thenAccept(books -> System.out.println(books));
            //System.out.println(books);

            System.out.println("Current profiles: " +
                    Arrays.asList(context.getEnvironment().getActiveProfiles()));

            System.out.println("Total bean count: " + context.getBeanDefinitionCount());
//            System.out.println("Our beans: " +
//                    Arrays.stream(context.getBeanDefinitionNames())
//                            .map(context::getBean)
//                            .map(Object::getClass)
//                            .map(Class::getSimpleName)
//                            .collect(Collectors.joining(",")))
            ;
        }

    }

}
