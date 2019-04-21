package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoAddNewContact();
      app.getContactHelper().createContact(
              new ContactInfo(
                      "Test",
                      "Testing",
                      "+35796095",
                      "test@mailinator.com",
                      "test1"
              ),
              true
      );
      app.goTo().gotoHomePage();
    }
    List<ContactInfo> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.acceptAlert();
    app.goTo().gotoHomePage();
    List<ContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
