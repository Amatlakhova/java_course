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
      String[] phones = element.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactInfo()
              .withId(id)
              .withFirstname(firstName)
              .withLastname(lastName)
              .withHomePhone(phones[0])
              .withMobilePhone(phones[1])
              .withWorkPhone(phones[2])
      );
    }
    return contacts;
  }

  public ContactInfo infoFromEditForm(ContactInfo contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
      return new ContactInfo()
              .withId(contact.getId())
              .withFirstname(firstname)
              .withLastname(lastname)
              .withHomePhone(home)
              .withMobilePhone(mobile)
              .withWorkPhone(work);

  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}





















