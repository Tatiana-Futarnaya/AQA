package ru.astondevs.lab10.task1.animal;

/**
 * @author Tatiana Futarnaya
 */
public class Bowl {
    private int foodAmount; // Количество еды в миске

    public Bowl(int initialFood) {
        this.foodAmount = initialFood;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("Добавлено " + amount + " еды в миску. Всего еды: " + foodAmount);
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды.");
        }
    }

    public boolean feedCat(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
            return true; // Еда была успешно съедена
        } else {
            System.out.println("Недостаточно еды в миске. Текущая еда: " + foodAmount);
            return false; // Недостаточно еды
        }
    }
}
