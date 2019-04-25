package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.goTo().addContact();
      app.contact().create(
              new ContactInfo()
                      .withFirstname("Test")
                      .withLastname("Testing")
                      .withMobile("+35796095")
                      .withEmail("test@mailinator.com")
                      .withGroup("test1"),
              true
      );
      app.goTo().gotoHomePage();
    }

  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactInfo modifiedContact = before.iterator().next();
    ContactInfo contact = new ContactInfo()
            .withId(modifiedContact.getId())
            .withFirstname("Test")
            .withLastname("Testing")
            .withMobile("+35796095")
            .withEmail("test@mailinator.com");
    app.contact().modify(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
