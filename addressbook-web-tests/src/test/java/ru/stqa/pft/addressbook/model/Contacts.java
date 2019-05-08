package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactInfo> {

  private Set<ContactInfo> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactInfo>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactInfo>();
  }

  public Contacts(Collection<ContactInfo> contacts) {
    this.delegate = new HashSet<ContactInfo>(contacts);
  }

  @Override
  protected Set<ContactInfo> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactInfo contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactInfo contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
