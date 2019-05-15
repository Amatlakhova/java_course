package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.tests.TestBase;

public class ResetPasswordHelper extends HelperBase{
  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsers() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void selectUser(int id) {
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public  void openResetLink(String link) {
    wd.get(link);
  }

  public void changePassword(String password) {
    type(By.id("password"), password);
    type(By.id("password-confirm"), password);
    click(By.className("button"));
  }
}
