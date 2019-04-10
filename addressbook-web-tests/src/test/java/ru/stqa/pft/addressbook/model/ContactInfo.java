package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactInfo {
  private int id;
  private final String firstname;
  private final String lastname;
  private final String mobile;
  private final String email;
  private String group;

  public void setId(int id) {
    this.id = id;
  }

  public ContactInfo(String firstname, String lastname, String mobile, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
  }

  public ContactInfo(int id, String firstname, String lastname, String mobile, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
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
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  public int getId() {
    return id;
  }

}
