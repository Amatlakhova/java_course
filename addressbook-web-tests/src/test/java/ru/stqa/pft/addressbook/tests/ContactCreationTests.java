package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    File photo = new File("src/test/resources/stru.png");
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactInfo.class);
      List<ContactInfo> contacts = (List<ContactInfo>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactInfo contact) {
    Contacts before = app.contact().all();
    app.goTo().addContact();
    app.contact().create(contact, true);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    app.goTo().addContact();
    ContactInfo contact = new ContactInfo()
            .withFirstname("Test'")
            .withLastname("Testing")
            .withMobile("+35796095")
            .withEmail("test@mailinator.com")
            .withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
