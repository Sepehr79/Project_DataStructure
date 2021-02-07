package database.interfaces;

import java.util.List;

public interface IDAO<DT> {

    /**
     * insert new row to the database
     * @param object will insert to the database
     */
    public void insertObject(DT object);

    /**
     * delete row from database
     * @param exampleObject example to remove objects that are same to the argument object
     */
    public void deleteObjects(DT exampleObject);

    /**
     * get object from database
     * @param exampleObject condition of gets witch object
     * @return founded object from database
     */
    public DT getObject(DT exampleObject);

    /**
     * get list of objects with input condition
     * @param condition
     * example of correct condition:
     *            age < 90 and age > 10
     * @return list of founded objects
     */
    public List<DT> getObjects(String condition);

    /**
     * update object in the database
     * @param editingObject that will update
     * @param newObject that will update to the database
     */
    public void updateObject(DT editingObject, DT newObject);

    /**
     * reset database
     */
    public  void resetDataBase();

}
