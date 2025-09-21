import java.util.*;

class MovieRentingSystem {

    class Movie {
        int shop;
        int movie;
        int price;
        
        Movie(int s, int m, int p) {
            shop = s;
            movie = m;
            price = p;
        }
    }
    
    // price lookup
    Map<String, Integer> priceMap;
    
    // available copies per movie
    Map<Integer, TreeSet<Movie>> availableMovies;
    
    // rented movies globally
    TreeSet<Movie> rentedMovies;
    
    public MovieRentingSystem(int n, int[][] entries) {
        priceMap = new HashMap<>();
        availableMovies = new HashMap<>();
        
        Comparator<Movie> searchCmp = (a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            return a.shop - b.shop;
        };
        
        Comparator<Movie> reportCmp = (a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            if (a.shop != b.shop) return a.shop - b.shop;
            return a.movie - b.movie;
        };
        
        rentedMovies = new TreeSet<>(reportCmp);
        
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            Movie mv = new Movie(shop, movie, price);
            priceMap.put(shop + "#" + movie, price);
            
            availableMovies.putIfAbsent(movie, new TreeSet<>(searchCmp));
            availableMovies.get(movie).add(mv);
        }
    }
    
    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();
        if (!availableMovies.containsKey(movie)) return result;
        
        TreeSet<Movie> set = availableMovies.get(movie);
        Iterator<Movie> it = set.iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            result.add(it.next().shop);
            count++;
        }
        return result;
    }
    
    public void rent(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        Movie mv = new Movie(shop, movie, price);
        
        availableMovies.get(movie).remove(mv);
        rentedMovies.add(mv);
    }
    
    public void drop(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        Movie mv = new Movie(shop, movie, price);
        
        rentedMovies.remove(mv);
        availableMovies.get(movie).add(mv);
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        Iterator<Movie> it = rentedMovies.iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            Movie mv = it.next();
            result.add(Arrays.asList(mv.shop, mv.movie));
            count++;
        }
        return result;
    }
}
