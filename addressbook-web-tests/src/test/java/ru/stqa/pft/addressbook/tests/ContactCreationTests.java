package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;


import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    List<ContactInfo> before = app.getContactHelper().getContactList();
    app.goTo().gotoAddNewContact();
    ContactInfo contact = new ContactInfo(
            "Test",
            "Testing",
            "+35796095",
            "test@mailinator.com",
            "test1"
    );
    app.getContactHelper().createContact(contact, true);
    app.goTo().gotoHomePage();
    List<ContactInfo> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactInfo> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
