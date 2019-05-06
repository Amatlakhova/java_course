package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactInfo {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String mobile;
  private String email;
  private String email2;
  private String email3;
  private String group;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;
  private String address;
  private String allEmails;
  private File photo;

  public ContactInfo withId(int id) {
    this.id = id;
    return this;
  }

  public ContactInfo withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactInfo withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactInfo withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactInfo withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactInfo withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactInfo withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactInfo withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactInfo withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactInfo withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactInfo withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactInfo withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactInfo withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactInfo withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactInfo withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public File getPhoto() {
    return photo;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail3() {
    return email3;
  }
  public String getEmail2() {
    return email2;
  }

  public String getGroup() {
    return group;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAddress() {
    return address;
  }

  public String getAllEmails() {
    return allEmails;
  }



  @Override
  public String toString() {
    return "ContactInfo{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactInfo that = (ContactInfo) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

}
