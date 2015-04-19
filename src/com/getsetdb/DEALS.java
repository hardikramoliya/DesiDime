package com.getsetdb;

public class DEALS {
	
	private String K_Title = "title";
	private String K_Store = "store";
	private String K_CurrentPrice = "current_price";
	private String K_OriginalPrice = "original_price";
	private String K_ShippingCharge = "shipping_charge";
	private String K_Store_Url = "store_url";
	private String K_Posted_UName = "posted_username";
	private String K_Posted_UImage = "posted_user_image";
	private String K_Category = "deal_category_name";

	private String K_POPULARITY_COUNT = "popularity_count";
	private String K_COMMENT_COUNT = "comments_count";
	private String K_OFFPERCENT = "off_percent";

	public DEALS() {
	}

	private static DEALS instance = new DEALS();

	public static DEALS getInstance() {
		return instance;
	}

	public DEALS(String title, String store, String current_price,
			String original_price, String shipping_charge, String store_url,
			String posted_username, String posted_user_image,
			String deal_category_name, String popularity_count,
			String comments_count, String off_percent) {
		this.K_Title = title;
		this.K_Store = store;
		this.K_CurrentPrice = current_price;
		this.K_OriginalPrice = original_price;
		this.K_ShippingCharge = shipping_charge;
		this.K_Store_Url = store_url;
		this.K_Posted_UName = posted_username;
		this.K_Posted_UImage = posted_user_image;
		this.K_Category = deal_category_name;
		this.K_POPULARITY_COUNT = popularity_count;
		this.K_COMMENT_COUNT = comments_count;
		this.K_OFFPERCENT = off_percent;
	}

	public void setsTitle(String title) {
		this.K_Title = title;
	}

	public String getTitle() {
		return this.K_Title;
	}

	public void setStore(String store) {
		this.K_Store = store;
	}

	public String getStore() {
		return this.K_Store;
	}

	public void setCurrentPrice(String current_price) {
		this.K_CurrentPrice = current_price;
	}

	public String getCurrentPrice() {
		return this.K_CurrentPrice;
	}

	public void setOriginalPrice(String speed) {
		this.K_OriginalPrice = speed;
	}

	public String getOriginalPrice() {
		return this.K_OriginalPrice;
	}

	public void setShippingCharge(String speed) {
		this.K_ShippingCharge = speed;
	}

	public String getShippingCharge() {
		return this.K_ShippingCharge;
	}

	public void setStore_Url(String speed) {
		this.K_Store_Url = speed;
	}

	public String getStore_Url() {
		return this.K_Store_Url;
	}

	public void setPosted_UName(String msg) {
		this.K_Posted_UName = msg;
	}

	public String getPosted_UName() {
		return this.K_Posted_UName;
	}

	public void setPosted_UImage(String msg) {
		this.K_Posted_UImage = msg;
	}

	public String getPosted_UImage() {
		return this.K_Posted_UImage;
	}

	public void setCategory(String msg) {
		this.K_Category = msg;
	}

	public String getCategory() {
		return this.K_Category;
	}

	public void setPopularityCount(String msg) {
		this.K_POPULARITY_COUNT = msg;
	}

	public String getPopularityCount() {
		return this.K_POPULARITY_COUNT;
	}

	public void setCommentCount(String msg) {
		this.K_COMMENT_COUNT = msg;
	}

	public String getCommentCount() {
		return this.K_COMMENT_COUNT;
	}

	public void setOffPercent(String msg) {
		this.K_OFFPERCENT = msg;
	}

	public String getOffPercent() {
		return this.K_OFFPERCENT;
	}

}