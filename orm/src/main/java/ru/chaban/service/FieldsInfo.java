package ru.chaban.service;

/*
    инфо для создания запросов в БД
 */
public class FieldsInfo {
    private String name;
    private String type;
    private String valueStr;
    private Boolean key;
    private Object value;

    public FieldsInfo(String name, String type, String valueStr, Boolean key, Object value) {
        if (!name.toUpperCase().equals("ID")) {
            this.name = "C_" + name;
        } else {
            this.name = name;
        }
        this.type = type;
        this.valueStr = valueStr;
        this.key = key;
        this.value = value;
    }

    public Boolean getKey() {
        return key;
    }

    public void setKey(Boolean key) {
        this.key = key;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public String getNameInClass() {
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

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    @Override
    public String toString() {
        return "FieldsInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + valueStr + '\'' +
                ", key=" + key +
                '}';
    }
}
