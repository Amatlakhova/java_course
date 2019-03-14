package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactInfo;

public class ContactHelper extends HelperBase {

  public ContactHelper (ChromeDriver wd) {
    super(wd);
  }

  public void fillNewContactForm(ContactInfo contactInfo) {
    type(By.name("firstname"), contactInfo.getFirstname());
    type(By.name("lastname"), contactInfo.getLastname());
    type(By.name("mobile"), contactInfo.getMobile());
    type(By.name("email"), contactInfo.getEmail());
  }

  public void submitForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void selectContact() {
      click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void editContact() {
    click(By.xpath("//td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }
}
