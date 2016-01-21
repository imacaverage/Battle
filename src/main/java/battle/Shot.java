package battle;

/**
 * Класс "Выстрел"
 * Created by iMacAverage on 21.01.16.
 */
public class Shot {

    /**
     * раунд боя
     */
    private final int round;

    /**
     * объект "Корабль", который стреляет
     */
    private final Ship fromShip;

    /**
     * объект "Корабль", по которому стреляют
     */
    private final Ship toShip;

    /**
     * вероятность поражения
     */
    private final double probability;

    /**
     * результат выстреала
     */
    private final boolean result;

    /**
     * Создать объект
     * @param round раунд боя
     * @param fromShip объект "Корабль", который стреляет
     * @param toShip объект "Корабль", по которому стреляют
     * @param probability вероятность выстрела
     * @param result результат выстрела
     */
    public Shot(int round, Ship fromShip, Ship toShip, double probability, boolean result) {
        this.round = round;
        this.fromShip = fromShip;
        this.toShip = toShip;
        this.probability = probability;
        this.result = result;
    }

    /**
     * Получить объект "Корабль", который стреляет
     * @return объект "Корабль", который стреляет
     */
    public Ship getFromShip() {
        return this.fromShip;
    }

    /**
     * Получить объект "Корабль", по которому стреляют
     * @return объект "Корабль", по которому стреляют
     */
    public Ship getToShip() {
        return this.toShip;
    }

    /**
     * Получить результат выстрела
     * @return результат выстрела
     */
    public boolean isResult() {
        return this.result;
    }

    /**
     * Получить вероятность поражения
     * @return вероятность поражения
     */
    public double getProbability() {
        return this.probability;
    }

    /**
     * Проверить вероятность поражения на ноль
     * @return true в случае успеха, иначе false
     */
    public boolean isZeroProbability() {
        return this.probability == 0;
    }

    /**
     * Получить раунд
     * @return раунд
     */
    public int getRound() {
        return this.round;
    }

    @Override
    public String toString() {
        return String.format("round: %d\n" +
                "fromShip: %s\n" +
                "toShip: %s\n" +
                "probability: %f\n" +
                "result: %b",
                this.getRound(),
                this.getFromShip().toString(),
                this.getToShip().toString(),
                this.getProbability(),
                this.isResult());
    }

}
