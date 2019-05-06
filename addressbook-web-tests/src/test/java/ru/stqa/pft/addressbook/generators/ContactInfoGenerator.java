package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactInfoGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactInfoGenerator generator = new ContactInfoGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactInfo> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsXml(List<ContactInfo> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactInfo.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactInfo> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactInfo contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getMobile(), contact.getEmail()));
    }
    writer.close();
  }

  private List<ContactInfo> generateContacts(int count) {
    List<ContactInfo> contacts = new ArrayList<ContactInfo>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactInfo()
              .withFirstname(String.format("Test %s", i))
              .withLastname(String.format("Testing %s", i))
              .withMobile(String.format("+35796095%s", i))
              .withEmail(String.format("test%s@mailinator.com", i)));
    }
    return contacts;
  }
}
