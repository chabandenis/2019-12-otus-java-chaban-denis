package ru.chaban.service;

/*
    инфо для создания запросов в БД
 */
public class FieldsInfo {
    private String name;
    private String type;
    private String value;
    private Boolean key;

    public FieldsInfo(String name, String type, String value, Boolean key) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.key = key;
    }

    public Boolean getKey() {
        return key;
    }

    public void setKey(Boolean key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FieldsInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", key=" + key +
                '}';
    }
}
