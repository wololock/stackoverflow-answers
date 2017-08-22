import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

final class Foo {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        FileDt dt = new FileDt();
        // #1
        PropertyUtils.setSimpleProperty(dt, "reportName", "abc.html");
        // #2
        PropertyUtils.setSimpleProperty(dt, "reportLocation", "c://");
    }
}
