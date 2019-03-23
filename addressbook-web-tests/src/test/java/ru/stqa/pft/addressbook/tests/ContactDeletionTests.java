package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewContact();
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
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.acceptAlert();
  }
}
