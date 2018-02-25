package scanner.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileHelper {

  public static List<File> getAllTargetXMLFile(String rootDir,String targetFileName) {
    List<File> target = new ArrayList<>();
    traverseFolder(rootDir, target,targetFileName);
    return target;
  }

  private static void traverseFolder(String rootPath, List<File> target,String targetFileName) {
    File file = new File(rootPath);
    if (file.exists()) {
      File[] files = file.listFiles();
      if (files.length == 0) {
        return;
      } else {
        for (File fileNode : files) {
          if (fileNode.isDirectory()) {
            traverseFolder(fileNode.getAbsolutePath(), target,targetFileName);
          } else {
            String fileName=fileNode.getName();
            boolean result= Pattern.compile(targetFileName).matcher(fileName).find();
            if(result){
              target.add(fileNode);
            }
          }
        }
      }
    }
  }
}
