package com.pages;

import com.microsoft.playwright.Page;

public class SearchUser {
    private Page page;
    private String usernameSearchLoc = "input[placeholder='Type for hints…']";  
    private String searchButtonLoc = "button[type='submit']";
    private String resultRowLoc = "//div[contains(@class,'oxd-table-row')]";  

    public SearchUser(Page page) {
        this.page = page;
    }

   

    public boolean isUserPresent() {
        return page.locator(resultRowLoc).count() > 0;
    }

	public void searchUser(String username) {
		  page.fill(usernameSearchLoc, username);
	        page.click(searchButtonLoc);
		
	}
}
