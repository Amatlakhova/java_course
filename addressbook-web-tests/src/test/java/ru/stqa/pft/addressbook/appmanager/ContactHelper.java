package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactInfo;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void fillNewContactForm(ContactInfo contactInfo, boolean creation) {
    type(By.name("firstname"), contactInfo.getFirstname());
    type(By.name("lastname"), contactInfo.getLastname());
    type(By.name("mobile"), contactInfo.getMobile());
    type(By.name("email"), contactInfo.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactInfo.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void editContactById(int id) {
    wd.findElement(By.cssSelector("a[href= 'edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }

  public void create(ContactInfo contact, boolean b) {
    fillNewContactForm(contact, true);
    submitForm();
  }

  public void modify(ContactInfo contact) {
    editContactById(contact.getId());
    fillNewContactForm(contact, false);
    submitContactModification();
  }

  public void delete(ContactInfo contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactInfo().withId(id).withFirstname(firstName).withLastname(lastName));
    }
    return contacts;
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

}
