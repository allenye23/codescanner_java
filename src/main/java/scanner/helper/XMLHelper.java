package scanner.helper;

import di.scanner.exception.XMLNotFoundException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLHelper {

  static List<String> soapClassNameList = new ArrayList<>();
  static Map<String,String> restfulClassNameMap= new HashMap();
  static List<String> restDtoClassNameList = new ArrayList<>();

  public static List<Document> parseXmlToDocuments(List<String> filePaths) throws XMLNotFoundException {
    List<Document> documents = new ArrayList<>();
    SAXReader reader = new SAXReader();
    String currentFilePath = "";
    try {
      for (String filepath : filePaths) {
        currentFilePath=filepath;
        documents.add(reader.read(filepath));
      }
      return documents;
    } catch (DocumentException e) {
      throw new XMLNotFoundException("Can not find the xml : " +currentFilePath);
    }
  }

  public static List<String> getTargetClassNameFromDocuments(List<Document> documents, boolean isUseThread) {
    List<String> tempTargetNameList = new ArrayList<>();
    Map<String,String> tempRestfulClassNameMap= new HashMap();
    if (isUseThread) {
      try {
        DocumentThreadHelper helper = new DocumentThreadHelper();
        tempTargetNameList.addAll(helper.handleList(documents, 4));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    } else {
      for (Document document : documents) {
        Element root = document.getRootElement();
        List<Element> subComponents = root.elements();

        for (Element subComponent : subComponents) {
          String name = subComponent.attributeValue("name");
          if(!name.contains("(")){
            soapClassNameList.add(name);
          }
          List<Element>  elements = subComponent.elements();
          elements.forEach(element -> {
            if (element.attributeValue("name").equals("WEB_SERVICE_URL")){
              String value = element.attributeValue("value");
              if (value.startsWith("http")){
                String url=element.attributeValue("value");
                Pattern pattern = Pattern.compile("wls_(prs|dom)_.+");
                Matcher matcher = pattern.matcher(url);
                if (matcher.find()){
                  url = matcher.group();
                }
                tempRestfulClassNameMap.put(name,url);
              }
            }
          });
        }
      }
    }


    removeFwProxy(tempRestfulClassNameMap);
    removeNonOOCLIris4ClassName();
    removeDuplicatedName();
    return soapClassNameList;
  }

  public static void removeFwProxy(Map<String,String> tempRestfulClassNameMap) {
    Set<Map.Entry<String, String>> restfulClassNameSet = tempRestfulClassNameMap.entrySet();
    Iterator<Map.Entry<String, String>> iterator = restfulClassNameSet.iterator();
    int index = 0;
    while (iterator.hasNext()) {
      Map.Entry<String,String> map=iterator.next();
      String name=map.getKey();

      index = name.indexOf("(");
      if (index > 0) {
        restfulClassNameMap.put(name.substring(index + 1, name.length() - 1),map.getValue());
        restDtoClassNameList.add(name.substring(index + 1, name.length() - 1));
      }
    }
  }

  public static List<String> getTargetClassNameByDocument(Document document) {
    List<String> targetClassNameList = new ArrayList<>();
    Element root = document.getRootElement();
    List<Element> subComponents = root.elements();
    for (Element subComponent : subComponents) {
      String name = subComponent.attributeValue("name");
      targetClassNameList.add(subComponent.attributeValue("name"));
      List<Attribute>  attributes = subComponent.attributes();
      attributes.forEach(attribute -> {
        if (attribute.getName().equals("WEB_SERVICE_URL")){
          String url=attribute.getValue();
          int indexOf=url.indexOf("}");
          url=url.substring(indexOf+2,url.length()-1);
          restfulClassNameMap.put(name,url);
        }
      });

    }
    return targetClassNameList;
  }


  public static void removeNonOOCLIris4ClassName() {
    boolean isTarget;
    for (int i = soapClassNameList.size() - 1; i >= 0; i--) {
      isTarget = Pattern.compile("com.oocl.*.").matcher(soapClassNameList.get(i)).find();
      if (!isTarget) {
        soapClassNameList.remove(i);
      }
    }
  }

  public static List<String> getSoapClassNameList() {
    return soapClassNameList;
  }

  static void removeDuplicatedName() {
    soapClassNameList = new ArrayList<>(new HashSet(soapClassNameList));
  }

  public static List<String> getRestDtoClassNameList() {
    return restDtoClassNameList;
  }

  public static Map<String, String> getRestfulClassNameMap() {
    return restfulClassNameMap;
  }
}
