package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {
  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String name, String password) {
    type(By.id("username"), name);
    type(By.id("password"), password);
    click(By.className("button"));
  }
}
