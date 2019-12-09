package com.systalk.sys.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="user_seq")
	private int userSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="login_date")
	private Date loginDate;

	private String suspend;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private String userId;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_pwd")
	private String userPwd;

	//bi-directional many-to-one association to AdSetting
	@OneToMany(mappedBy="user")
	private List<AdSetting> adSettings;

	//bi-directional many-to-one association to Article
	@OneToMany(mappedBy="user")
	private List<Article> articles;

	//bi-directional many-to-one association to ArticleSchedule
	@OneToMany(mappedBy="user")
	private List<ArticleSchedule> articleSchedules;

	//bi-directional many-to-one association to BannerSetting
	@OneToMany(mappedBy="user")
	private List<BannerSetting> bannerSettings;

	//bi-directional many-to-one association to Logowall
	@OneToMany(mappedBy="user")
	private List<Logowall> logowalls;

	//bi-directional many-to-one association to SuccessCase
	@OneToMany(mappedBy="user")
	private List<SuccessCase> successCases;

	//bi-directional many-to-one association to VideoSetting
	@OneToMany(mappedBy="user")
	private List<VideoSetting> videoSettings;

	public User() {
	}

	public int getUserSeq() {
		return this.userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getSuspend() {
		return this.suspend;
	}

	public void setSuspend(String suspend) {
		this.suspend = suspend;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public List<AdSetting> getAdSettings() {
		return this.adSettings;
	}

	public void setAdSettings(List<AdSetting> adSettings) {
		this.adSettings = adSettings;
	}

	public AdSetting addAdSetting(AdSetting adSetting) {
		getAdSettings().add(adSetting);
		adSetting.setUser(this);

		return adSetting;
	}

	public AdSetting removeAdSetting(AdSetting adSetting) {
		getAdSettings().remove(adSetting);
		adSetting.setUser(null);

		return adSetting;
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setUser(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setUser(null);

		return article;
	}

	public List<ArticleSchedule> getArticleSchedules() {
		return this.articleSchedules;
	}

	public void setArticleSchedules(List<ArticleSchedule> articleSchedules) {
		this.articleSchedules = articleSchedules;
	}

	public ArticleSchedule addArticleSchedule(ArticleSchedule articleSchedule) {
		getArticleSchedules().add(articleSchedule);
		articleSchedule.setUser(this);

		return articleSchedule;
	}

	public ArticleSchedule removeArticleSchedule(ArticleSchedule articleSchedule) {
		getArticleSchedules().remove(articleSchedule);
		articleSchedule.setUser(null);

		return articleSchedule;
	}

	public List<BannerSetting> getBannerSettings() {
		return this.bannerSettings;
	}

	public void setBannerSettings(List<BannerSetting> bannerSettings) {
		this.bannerSettings = bannerSettings;
	}

	public BannerSetting addBannerSetting(BannerSetting bannerSetting) {
		getBannerSettings().add(bannerSetting);
		bannerSetting.setUser(this);

		return bannerSetting;
	}

	public BannerSetting removeBannerSetting(BannerSetting bannerSetting) {
		getBannerSettings().remove(bannerSetting);
		bannerSetting.setUser(null);

		return bannerSetting;
	}

	public List<Logowall> getLogowalls() {
		return this.logowalls;
	}

	public void setLogowalls(List<Logowall> logowalls) {
		this.logowalls = logowalls;
	}

	public Logowall addLogowall(Logowall logowall) {
		getLogowalls().add(logowall);
		logowall.setUser(this);

		return logowall;
	}

	public Logowall removeLogowall(Logowall logowall) {
		getLogowalls().remove(logowall);
		logowall.setUser(null);

		return logowall;
	}

	public List<SuccessCase> getSuccessCases() {
		return this.successCases;
	}

	public void setSuccessCases(List<SuccessCase> successCases) {
		this.successCases = successCases;
	}

	public SuccessCase addSuccessCas(SuccessCase successCas) {
		getSuccessCases().add(successCas);
		successCas.setUser(this);

		return successCas;
	}

	public SuccessCase removeSuccessCas(SuccessCase successCas) {
		getSuccessCases().remove(successCas);
		successCas.setUser(null);

		return successCas;
	}

	public List<VideoSetting> getVideoSettings() {
		return this.videoSettings;
	}

	public void setVideoSettings(List<VideoSetting> videoSettings) {
		this.videoSettings = videoSettings;
	}

	public VideoSetting addVideoSetting(VideoSetting videoSetting) {
		getVideoSettings().add(videoSetting);
		videoSetting.setUser(this);

		return videoSetting;
	}

	public VideoSetting removeVideoSetting(VideoSetting videoSetting) {
		getVideoSettings().remove(videoSetting);
		videoSetting.setUser(null);

		return videoSetting;
	}

}