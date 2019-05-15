package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class DeleteContactFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();

    for (GroupData group : groups) {
      if (group.getContacts().size() > 0) {
        return;
      }
    }

    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

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

    GroupData group = app.db().groups().iterator().next();
    ContactInfo contact = app.db().contacts().iterator().next();

    app.goTo().gotoHomePage();
    app.contact().selectContactById(contact.getId());
    app.contact().addToGroup(group.getId());
  }

  @Test
  public void testDeleteContactFromGroup() {
    Groups groups = app.db().groups();
    GroupData selectedGroup = null;

    for (GroupData group : groups) {
      if (group.getContacts().size() > 0) {
        selectedGroup = group;
      }
    }

    Contacts contacts = selectedGroup.getContacts();
    ContactInfo contact = contacts.iterator().next();

    app.goTo().gotoHomePage();
    app.contact().selectContactById(contact.getId());
    app.contact().removeFromGroup();

    groups = app.db().groups();

    for (GroupData group : groups) {
      if (group.equals(selectedGroup)) {
        selectedGroup = group;
      }
    }

    assertThat(selectedGroup.getContacts(), equalTo(contacts.without(contact)));
  }
}
