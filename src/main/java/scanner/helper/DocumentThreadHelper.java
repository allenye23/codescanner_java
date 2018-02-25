package scanner.helper;

import org.dom4j.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DocumentThreadHelper {

  public List<String> tempTargetClassNameList = new ArrayList<>();

  public synchronized List<String> handleList(List<Document> documents, int threadNum) throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(threadNum);
    int length = documents.size();
    int tl = length % threadNum == 0 ? length / threadNum : (length / threadNum + 1);
    for (int i = 0; i < threadNum; i++) {
      int end = (i + 1) * tl;
      if ((i * tl) <= length) {
        RunnableThread thread = new RunnableThread(documents, i * tl, end > length ? length : end, latch);
        Thread runable = new Thread(thread);
        runable.start();
      }
    }
    latch.await();
    return tempTargetClassNameList;
  }

  // 实现Runnable
  class RunnableThread implements Runnable {
    private List<Document> documents;
    CountDownLatch latch;
    private int start;
    private int end;

    public RunnableThread(List<Document> documents, int start, int end, CountDownLatch latch) {
      this.documents = documents;
      this.start = start;
      this.end = end;
      this.latch = latch;
    }

    @Override
    public void run() {
      List<Document> documentList = this.documents.subList(start, end);
      for (int i = 0; i < documentList.size(); i++) {
        tempTargetClassNameList.addAll(XMLHelper.getTargetClassNameByDocument(documentList.get(i)));
      }
      latch.countDown();
    }
  }
}
