import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public final class FileDt {
    String reportName;
    String reportLocation;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        FileDt dt = new FileDt();
        // #1
        PropertyUtils.setSimpleProperty(dt, "reportName", "abc.html");
        // #2
        PropertyUtils.setSimpleProperty(dt, "reportLocation", "c://");
    }
}
