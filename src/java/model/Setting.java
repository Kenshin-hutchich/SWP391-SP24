/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author KENSHIN
 */
public class Setting {

    private int id;
    private String name;
    private String value;
    private int order;
    private boolean status;

    /*
    Default settings:
    1. ('Role', 'Guest', 4, true),
    2. ('Role', 'Customer', 3, true),
    3. ('Role', 'Expert', 2, true),
    4. ('Role', 'Admin', 1, true),
    5. ('Action', 'Create', 1, true),
    6. ('Action', 'Read', 1, true),
    7. ('Action', 'Update', 1, true),
    8. ('Action', 'Delete', 1, true),
    9. ('Action', 'Common', 1, true),
    10. ('Objective', 'User', 1, true),
    11. ('Objective', 'Course', 1, true),
    12. ('Objective', 'Category', 1, true),
    13. ('Objective', 'Post', 1, true),
    14. ('Objective', 'Lesson', 1, true),
    15. ('Objective', 'Comment', 1, true).
    16. ('Objective', 'Quiz', 1, true).
     */
    // Constructors
    public Setting() {
    }

    public Setting(int id, String name, String value, int inorder, boolean status) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.order = inorder;
        this.status = status;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Setting{" + "id=" + id + ", name=" + name + ", value=" + value + ", inorder=" + order + ", status=" + status + '}';
    }

}
