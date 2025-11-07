
/**
 *
 * @author leonardo
 */
abstract class Repository {

    /**
     * This abtract method is overrited in difentes repositories, for create
     * Files
     *
     */
    public abstract void createFile();

    /**
     * This abtract method is overrited in difentes repositories, for add items
     *
     */
    public abstract void addItem();

    /**
     * This abtract method is overrited in difentes repositories, for load items
     *
     */
    
    
    public abstract void loadItem();

    /**
     * This abtract method is overrited in difentes repositories, for save items
     *
     */
    public abstract void saveItem();

    /**
     * This abtract method is overrited in difentes repositories, for delete
     * Files
     *
     */
    public abstract void deleteFile();

}
