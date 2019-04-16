package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//tr/td[8]/a")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactInfo contact, boolean b) {
    fillNewContactForm(contact, true);
    submitForm();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactInfo> getContactList() {
    List<ContactInfo> contacts = new ArrayList<ContactInfo>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactInfo contact = new ContactInfo(
              id,
              element.findElement(By.cssSelector("td:nth-child(3)")).getText(),
              element.findElement(By.cssSelector("td:nth-child(2)")).getText()
      );
      contacts.add(contact);
    }
    return contacts;
  }
}
