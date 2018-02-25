package scanner.analyzer;

import java.util.List;

public interface Analyzer<T> {

    List<T> analysis(Object obj);

}
