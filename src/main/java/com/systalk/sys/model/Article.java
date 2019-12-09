package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the article database table.
 * 
 */
@Entity
@Table(name = "article")
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="atc_seq")
	private int atcSeq;

	@Column(name="atc_topic")
	private String atcTopic;

	private String author;

	@Column(name="category_seq")
	private int categorySeq;

	@Lob
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	//bi-directional one-to-one association to CodeTable
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_seq", insertable = false, updatable = false)
	private CodeTable codeTable;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_seq")
	private User user;

	//bi-directional one-to-one association to ArticleClickCount
	@OneToOne(mappedBy="article", fetch=FetchType.LAZY)
	private ArticleClickCount articleClickCount;

	//bi-directional many-to-one association to ArticleLabelRel
	@OneToMany(mappedBy="article")
	private List<ArticleLabelRel> articleLabelRels;

	//bi-directional many-to-one association to ArticleSetting
	@OneToMany(mappedBy="article")
	private List<ArticleSetting> articleSettings;

	public Article() {
	}

	public int getAtcSeq() {
		return this.atcSeq;
	}

	public void setAtcSeq(int atcSeq) {
		this.atcSeq = atcSeq;
	}

	public String getAtcTopic() {
		return this.atcTopic;
	}

	public void setAtcTopic(String atcTopic) {
		this.atcTopic = atcTopic;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCategorySeq() {
		return this.categorySeq;
	}

	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPublishingDate() {
		return this.publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public CodeTable getCodeTable() {
		return this.codeTable;
	}

	public void setCodeTable(CodeTable codeTable) {
		this.codeTable = codeTable;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArticleClickCount getArticleClickCount() {
		return this.articleClickCount;
	}

	public void setArticleClickCount(ArticleClickCount articleClickCount) {
		this.articleClickCount = articleClickCount;
	}

	public List<ArticleLabelRel> getArticleLabelRels() {
		return this.articleLabelRels;
	}

	public void setArticleLabelRels(List<ArticleLabelRel> articleLabelRels) {
		this.articleLabelRels = articleLabelRels;
	}

	public ArticleLabelRel addArticleLabelRel(ArticleLabelRel articleLabelRel) {
		getArticleLabelRels().add(articleLabelRel);
		articleLabelRel.setArticle(this);

		return articleLabelRel;
	}

	public ArticleLabelRel removeArticleLabelRel(ArticleLabelRel articleLabelRel) {
		getArticleLabelRels().remove(articleLabelRel);
		articleLabelRel.setArticle(null);

		return articleLabelRel;
	}

	public List<ArticleSetting> getArticleSettings() {
		return this.articleSettings;
	}

	public void setArticleSettings(List<ArticleSetting> articleSettings) {
		this.articleSettings = articleSettings;
	}

	public ArticleSetting addArticleSetting(ArticleSetting articleSetting) {
		getArticleSettings().add(articleSetting);
		articleSetting.setArticle(this);

		return articleSetting;
	}

	public ArticleSetting removeArticleSetting(ArticleSetting articleSetting) {
		getArticleSettings().remove(articleSetting);
		articleSetting.setArticle(null);

		return articleSetting;
	}

}