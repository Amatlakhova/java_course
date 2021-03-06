package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private final ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    String user = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(user, password);
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL(app.getProperty("web.connectionPort")));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    String user = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories
            (user, password, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(user, password, issueData);
    IssueData createdIssueData = mc.mc_issue_get(user, password, issueId);
    return new Issue()
            .withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(
                    new Project()
                            .withId(createdIssueData.getId().intValue())
                            .withName(createdIssueData.getProject().getName())
            );

  }

  public Issue getIssue(int id) throws MalformedURLException, ServiceException, RemoteException {
    String user = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    IssueData issueData = getMantisConnect().mc_issue_get(user, password, BigInteger.valueOf(id));

    return new Issue()
            .withId(issueData.getId().intValue())
            .withSummary(issueData.getSummary())
            .withDescription(issueData.getDescription())
            .withStatus(issueData.getStatus().getName())
            .withProject(
                    new Project()
                            .withId(issueData.getId().intValue())
                            .withName(issueData.getProject().getName())
            );

  }
}
