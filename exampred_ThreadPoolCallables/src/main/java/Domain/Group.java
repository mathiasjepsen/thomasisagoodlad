package Domain;

/**
 *
 * @author mathiasjepsen
 */
public class Group {
    
    private String group;
    private String className;
    private String number;

    public Group(String group, String className, String number) {
        this.group = group;
        this.className = className;
        this.number = number;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
    
    
    
}
