package model;

/**
 * Created by orange on 16/9/22.
 */
public class Animal implements Comparable{
    private String animalId;
    private String pos;

    public Animal(String animalId, String pos) {
        this.animalId = animalId;
        this.pos = pos;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public int compareTo(Object o) {
        Animal temp = ((Animal)o);
        if (this.animalId!=null)
            return this.animalId.compareTo(temp.getAnimalId());
        else {
            return -1;
        }
    }
}
