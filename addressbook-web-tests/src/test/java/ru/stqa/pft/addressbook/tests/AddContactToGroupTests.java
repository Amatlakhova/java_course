package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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

    Groups groups = app.db().groups();

    for (GroupData group : groups) {
      if (group.getContacts().size() == 0) {
        return;
      }
    }

    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1"));
  }

  @Test
  public void testAddContactToGroup() {
    Groups groups = app.db().groups();
    GroupData selectedGroup = null;

    for (GroupData group : groups) {
      if (group.getContacts().size() == 0) {
        selectedGroup = group;
      }
    }
    ContactInfo contact = app.db().contacts().iterator().next();
    Contacts contacts = selectedGroup.getContacts();

    app.goTo().gotoHomePage();
    app.contact().selectContactById(contact.getId());
    app.contact().addToGroup(selectedGroup.getId());
    app.goTo().gotoHomePage();

    groups = app.db().groups();

    for (GroupData group : groups) {
      if (group.equals(selectedGroup)) {
        selectedGroup = group;
      }
    }

    assertThat(selectedGroup.getContacts(), equalTo(contacts.withAdded(contact)));
  }
}
