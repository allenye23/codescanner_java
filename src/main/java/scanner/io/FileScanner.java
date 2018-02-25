package scanner.io;

import scanner.exception.XMLNotFoundException;
import scanner.filter.ConfigureXMLFileFilter;
import scanner.filter.JavaSourceFileFilter;
import scanner.handler.FileHandler;
import scanner.handler.Handler;
import scanner.handler.HandlerDecorator;
import scanner.helper.XMLHelper;
import scanner.producer.ConfigureXMLProducer;
import scanner.producer.JavaSourceFileProducer;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileScanner {

    private HandlerDecorator<File> handlerDecorator;

    public FileScanner() {
        Handler javaSourceFileHandler = new FileHandler(new JavaSourceFileProducer(),new JavaSourceFileFilter());
        Handler configureXMLFileHandler = new FileHandler(new ConfigureXMLProducer(),new ConfigureXMLFileFilter());
        handlerDecorator = new HandlerDecorator<>(Arrays.asList(javaSourceFileHandler,configureXMLFileHandler));
    }

    public void scan(String path){
        File  rootFile = new File(path);
        if (!rootFile.exists()){
            System.out.println("not a path");
            return;
        }
        LinkedList<File> fileLinkedList = new LinkedList<File>();
        File[] files = rootFile.listFiles();
        scan(fileLinkedList, files);
        File tempFile ;
        while (!fileLinkedList.isEmpty()){
            tempFile = fileLinkedList.removeFirst();
            files = tempFile.listFiles();
             scan(fileLinkedList, files);
        }
        handlerDecorator.handle(null);//终结标志

//        scanXML();

    }

//    private void scanXML() {
//        try {
//            List<String> targetNames= XMLHelper.getTargetClassNameFromDocuments(XMLHelper.parseXmlToDocuments(XMLStore.getInstance().getXmlFilePaths()),false);
//            for(String name:targetNames){
//                System.out.println(name);
//            }
//            List<String> restful = XMLHelper.getRestDtoClassNameList();
//            for (String str:restful) {
//                System.out.println(str);
//            }
//        } catch (XMLNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    private void scan(LinkedList<File> fileLinkedList, File[] files) {
        for (File file:files){
            if (file.isDirectory()){
                fileLinkedList.add(file);
            } else {
                handlerDecorator.handle(file);
            }
        }

    }


}
