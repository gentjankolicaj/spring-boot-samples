package springboot.samples.jwt01.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "news_id")
  private Long newsId;

  @Column(name = "news_title")
  private String newsTitle;

  @Column(name = "news_subject")
  private String newsSubject;

  @Column(name = "news_content")
  private String newsContent;

  public News() {
    super();
    // TODO Auto-generated constructor stub
  }

  public News(Long newsId, String newsTitle, String newsSubject, String newsContent) {
    super();
    this.newsId = newsId;
    this.newsTitle = newsTitle;
    this.newsSubject = newsSubject;
    this.newsContent = newsContent;
  }

  public Long getNewsId() {
    return newsId;
  }

  public void setNewsId(Long newsId) {
    this.newsId = newsId;
  }

  public String getNewsTitle() {
    return newsTitle;
  }

  public void setNewsTitle(String newsTitle) {
    this.newsTitle = newsTitle;
  }

  public String getNewsSubject() {
    return newsSubject;
  }

  public void setNewsSubject(String newsSubject) {
    this.newsSubject = newsSubject;
  }

  public String getNewsContent() {
    return newsContent;
  }

  public void setNewsContent(String newsContent) {
    this.newsContent = newsContent;
  }


}
