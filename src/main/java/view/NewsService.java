package view;

import model.News;
import storage.api.INewsRepository;
import view.api.INewsService;

public class NewsService implements INewsService {

    private final INewsRepository repository;

    public NewsService(INewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(String header, String text) {
        News news = new News(text, header);
        repository.save(news);
    }
}
