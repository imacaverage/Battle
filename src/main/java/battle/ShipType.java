package battle;

/**
 * Класс "Тип корабля"
 * Created by iMacAverage on 21.01.16.
 */
public class ShipType {

    private final String name;

    private final int gun;

    private final double weapon;

    private final double shield;

    private final double cost;

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
        this.cost = ShipType.calcCost(this.gun, this.weapon, this.shield);
    }

    /**
     * Рассчитать стоимость корабля
     * @param gun количество орудий
     * @param weapon мощность орудия
     * @param shield мощность защиты
     * @return стоимость корабля
     */
    public static double calcCost(int gun, double weapon, double shield) {
        return (gun * weapon + 1) * (shield + 1);
    }

    /**
     * наименование типа
     */ /**
     * Получить наименование типа
     * @return наименование типа
     */
    public String getName() {
        return this.name;
    }

    /**
     * количество орудий
     */ /**
     * Получить количество орудий
     * @return количество орудий
     */
    public int getGun() {
        return this.gun;
    }

    /**
     * мощность орудия
     */ /**
     * Получить мощность орудия
     * @return мощность орудия
     */
    public double getWeapon() {
        return this.weapon;
    }

    /**
     * мощность защиты
     */ /**
     * Получить мощность защиты
     * @return мощность защиты
     */
    public double getShield() {
        return this.shield;
    }

    /**
     * стоимость
     */ /**
     * Получить стоимость
     * @return стоимость
     */
    public double getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        return String.format("type: %s, weapon: %f, shield: %f",
                this.getName(),
                this.getWeapon(),
                this.getShield());
    }

}
