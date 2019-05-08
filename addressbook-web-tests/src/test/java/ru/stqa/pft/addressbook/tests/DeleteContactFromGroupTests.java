package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.testng.Assert.assertTrue;

public class DeleteContactFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    GroupData group = new GroupData().withName("forDeletion");
    app.goTo().groupPage();
    app.group().create(group);
    app.goTo().addContact();
    app.contact().create(
            new ContactInfo()
                    .withFirstname("Test")
                    .withLastname("Testing")
                    .withMobile("+35796095")
                    .withEmail("test@mailinator.com")
                    .inGroup(group),
            true
    );
    app.goTo().gotoHomePage();
  }

  @Test
  public void testDeleteContactFromGroup() {
    app.goTo().gotoHomePage();
    app.contact().selectGroup("forDeletion");
    ContactInfo contact = app.contact().all().iterator().next();
    app.contact().selectContactById(contact.getId());
    app.contact().removeFromGroup();

    GroupData group = app.db().groups().stream().filter((g) -> g.getName().equals("forDeletion")).iterator().next();
    assertTrue(group.getContacts().size() == 0);

    app.goTo().groupPage();
    app.group().delete(group);
  }
}
