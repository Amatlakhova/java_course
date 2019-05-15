package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

  @Test
  public void testResetPassword() throws IOException, MessagingException {

    List<UserData> users = app.db().users();

    UserData selectedUser = null;
    for (UserData user : users) {
      if (user.getName().equals("user")) {
        selectedUser = user;
      }
    }

    if (!app.james().doesUserExist(selectedUser.getName())) {
      app.james().createUser(selectedUser.getName(), "password");
    }

    String admin = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    app.login().login(admin, password);

    app.resetPassword().manageUsers();
    app.resetPassword().selectUser(selectedUser.getId());
    app.resetPassword().resetPassword();

    List<MailMessage> mailMessages = app.james().waitForMail(selectedUser.getName(), "password", 120000);
    String resetLink = findResetLink(mailMessages, selectedUser.getEmail());

    String newPassword = String.format("password%s", System.currentTimeMillis());
    app.resetPassword().openResetLink(resetLink);
    app.resetPassword().changePassword(newPassword);

    HttpSession session = app.newSession();
    assertTrue(session.login(selectedUser.getName(), newPassword));
    assertTrue(session.isLoggedInAs(selectedUser.getName()));
  }

  private String findResetLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
