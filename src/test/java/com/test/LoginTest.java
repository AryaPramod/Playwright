package com.test;

import org.testng.annotations.Test;

import com.pages.AdminPage;
import com.pages.DeleteUserPage;
import com.pages.EditUserPage;
import com.pages.LoginPage;
import com.pages.SearchUser;
import com.utilities.BaseTest;
import com.utilities.Config;


public class LoginTest extends BaseTest {
    @Test
    public void testCreateAdminUser() {
        String username = Config.readPropertyFileData("userName", "config");
        String password = Config.readPropertyFileData("password", "config");
        String empName = Config.readPropertyFileData("empName", "config");
        String newUserName = Config.readPropertyFileData("newUserName", "config");
        String newUserPassword = Config.readPropertyFileData("newUserPassword", "config");
        String newUserConfirmPassword = Config.readPropertyFileData("newUserConfirmPassword", "config");
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(username, password);
        page.waitForSelector("text=Dashboard");
        AdminPage adminPage = new AdminPage(page);
        adminPage.openAdminSection();
        adminPage.clickAddButton();
        adminPage.fillUserDetails(empName, newUserName, newUserPassword, newUserConfirmPassword);
        adminPage.saveUser();
        page.waitForSelector("text=Successfully Saved");
        System.out.println("New admin user created successfully.");
        SearchUser searchUserPage = new SearchUser(page);
        String searchRole = Config.readPropertyFileData("searchRole", "config");
        String searchStatus = Config.readPropertyFileData("searchStatus", "config");
        searchUserPage.searchUser(username);
        EditUserPage edituserpage = new EditUserPage(page);
        String newStatus = Config.readPropertyFileData("newStatus", "config");
        edituserpage.editUserStatus(newUserName, newStatus);
        DeleteUserPage deleteUserPage = new DeleteUserPage(page);
        deleteUserPage.deleteUser();
    }
    }