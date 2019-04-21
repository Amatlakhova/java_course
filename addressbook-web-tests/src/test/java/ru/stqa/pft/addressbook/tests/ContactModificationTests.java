package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
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
    app.getContactHelper().editContact(before.size() - 1);
    ContactInfo contact = new ContactInfo(
            before.get(before.size() - 1).getId(),
            "Test",
            "Testing",
            "+35796095",
            "test@mailinator.com",
            null
    );
    app.getContactHelper().fillNewContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.goTo().gotoHomePage();
    List<ContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactInfo> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
