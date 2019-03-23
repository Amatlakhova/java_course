package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillNewContactForm(new ContactInfo("Test", "Testing", "+35796095", "test@mailinator.com", null), false);
    app.getContactHelper().submitContactModification();
  }
}
