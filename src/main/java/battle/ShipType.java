package battle;

/**
 * Класс "Тип корабля"
 * Created by iMacAverage on 21.01.16.
 */
public class ShipType {

    /**
     * наименование типа
     */
    private final String name;

    /**
     * количество орудий
     */
    private final int gun;

    /**
     * мощность орудия
     */
    private final double weapon;

    /**
     * мощность защиты
     */
    private final double shield;

    /**
     * Создать объект
     * @param name наименование типа
     * @param gun количество орудий
     * @param weapon мощность орудия
     * @param shield мощность защиты
     */
    public ShipType(String name, int gun, double weapon, double shield) {
        this.name = name;
        this.gun = gun;
        this.weapon = weapon;
        this.shield = shield;
    }

    /**
     * Получить наименование типа
     * @return наименование типа
     */
    public String getName() {
        return this.name;
    }

    /**
     * Получить количество орудий
     * @return количество орудий
     */
    public int getGun() {
        return this.gun;
    }

    /**
     * Получить мощность орудия
     * @return мощность орудия
     */
    public double getWeapon() {
        return this.weapon;
    }

    /**
     * Получить мощность защиты
     * @return мощность защиты
     */
    public double getShield() {
        return this.shield;
    }

    @Override
    public String toString() {
        return String.format("type: %s, weapon: %f, shield: %f",
                this.getName(),
                this.getWeapon(),
                this.getShield());
    }

}
