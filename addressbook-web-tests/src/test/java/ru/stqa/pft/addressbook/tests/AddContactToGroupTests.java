package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().addContact();
      app.contact().create(
              new ContactInfo()
                      .withFirstname("Test")
                      .withLastname("Testing")
                      .withMobile("+35796095")
                      .withEmail("test@mailinator.com"),
              true
      );
      app.goTo().gotoHomePage();
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testAddContactToGroup() {
    GroupData group = app.db().groups().iterator().next();
    ContactInfo contact = app.db().contacts().iterator().next();
    app.goTo().gotoHomePage();
    app.contact().selectContactById(contact.getId());
    app.contact().addToGroup(group.getId());
    app.goTo().gotoHomePage();

    group = app.db().groups().iterator().next();

    assertThat(group.getContacts().size() - 1, equalTo(group.getContacts().without(contact).size()));
  }
}
