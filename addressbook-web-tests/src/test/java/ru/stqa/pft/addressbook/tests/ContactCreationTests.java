package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addContact();
    ContactInfo contact = new ContactInfo()
            .withFirstname("Test")
            .withLastname("Testing")
            .withMobile("+35796095")
            .withEmail("test@mailinator.com")
            .withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
