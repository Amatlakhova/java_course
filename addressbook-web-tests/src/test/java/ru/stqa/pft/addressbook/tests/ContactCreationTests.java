package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillNewContactForm(new ContactInfo("Test", "Testing", "+35796095", "test@mailinator.com", "test1"), true);
    app.getContactHelper().submitForm();
  }

}
