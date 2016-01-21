package battle;

/**
 * Класс "Раса"
 * Created by iMacAverage on 21.01.16.
 */
public class Race {

    /**
     * уникальный номер расы
     */
    private final int id;

    /**
     * наименование расы
     */
    private final String name;

    /**
     * Создать объект
     * @param id уникальный номер расы
     * @param name наименование расы
     */
    public Race(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Получить уникальный номер расы
     * @return уникальный номер расы
     */
    public int getId() {
        return id;
    }

    /**
     * Получить наименование расы
     * @return наименование расы
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("race: %s",
                this.getName());

    }

}
