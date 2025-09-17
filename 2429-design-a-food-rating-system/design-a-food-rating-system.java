import java.util.*;

class FoodRatings {
    // Maps
    private Map<String, String> foodToCuisine;
    private Map<String, Integer> foodToRating;
    private Map<String, PriorityQueue<Food>> cuisineToPQ;

    // Helper class for heap
    private static class Food {
        String name;
        int rating;
        Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }
    }

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToPQ = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToPQ
                .computeIfAbsent(cuisine, k -> new PriorityQueue<>(
                        (a, b) -> a.rating != b.rating 
                                  ? b.rating - a.rating 
                                  : a.name.compareTo(b.name)
                ))
                .add(new Food(food, rating));
        }
    }

    public void changeRating(String food, int newRating) {
        foodToRating.put(food, newRating);
        String cuisine = foodToCuisine.get(food);
        cuisineToPQ.get(cuisine).add(new Food(food, newRating)); // lazy add
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineToPQ.get(cuisine);

        // Lazy removal of outdated entries
        while (true) {
            Food top = pq.peek();
            if (top.rating == foodToRating.get(top.name)) {
                return top.name;
            }
            pq.poll(); // remove outdated entry
        }
    }
}
