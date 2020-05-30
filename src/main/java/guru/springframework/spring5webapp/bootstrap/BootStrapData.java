package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.entity.Author;
import guru.springframework.spring5webapp.entity.Book;
import guru.springframework.spring5webapp.entity.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner{

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher p1 = new Publisher("Lukas Molcan Company","Odbojarska 9","Bratislava","Slovakia",98765);
        publisherRepository.save(p1);

        Author a1 = new Author("Eric","Evans");
        Book b1 = new Book("Domain Driven Design","123123");

        a1.getBookSet().add(b1);
        b1.getAuthorSet().add(a1);

        b1.setPublisher(p1);
        p1.getBookSet().add(b1);

        authorRepository.save(a1);
        bookRepository.save(b1);
        publisherRepository.save(p1);

        Author a2 = new Author("Rod","Johnson");
        Book b2 = new Book("J2EE Development without EJB","3939459459");

        a2.getBookSet().add(b2);
        b2.getAuthorSet().add(a2);

        b2.setPublisher(p1);
        p1.getBookSet().add(b2);

        authorRepository.save(a2);
        bookRepository.save(b2);
        publisherRepository.save(p1);

        System.out.println("Number of Books: "+bookRepository.count());
        System.out.println("Number of books for Publisher: "+p1.getBookSet().size());
    }
}
