package scanner.filter;

import java.io.File;

public class ConfigureXMLFileFilter extends AbstractFileFilter{


    @Override
    public boolean filter(File file) {
        if("soaProxyConfigurations.xml".equals(file.getName())){
            return true;
        }
        return false;
    }
}
