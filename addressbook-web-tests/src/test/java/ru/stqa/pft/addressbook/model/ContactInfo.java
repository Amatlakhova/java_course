package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactInfo {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String mobile;
  private String email;
  private String group;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;


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

  public int getId() {
    return id;
  }

}
