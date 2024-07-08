package Service;

import Models.Article;

public class ArticleService {
    private Article selectedArticle;

    private static ArticleService instance = new ArticleService();

    private ArticleService() { }

    public static ArticleService getInstance() {
        return instance;
    }

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }
}
