package com.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    private String usernameLoc = "input[name='username']";
    private String passwordLoc = "input[name='password']";
    private String loginButtonLoc = "button[type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLogin() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void login(String username, String password) {
        page.fill(usernameLoc, username);
        page.fill(passwordLoc, password);
        page.click(loginButtonLoc);
    }
}
