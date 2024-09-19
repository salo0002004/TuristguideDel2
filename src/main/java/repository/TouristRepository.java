package com.example.touristguide.repository;

import com.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        attractions.add(new TouristAttraction("Tivoli Gardens", "Copenhagen, Denmark", 4.5, Arrays.asList("Family", "Amusement")));
        attractions.add(new TouristAttraction("The Little Mermaid", "Copenhagen, Denmark", 4.2, Arrays.asList("Historical", "Sculpture")));
    }

    public List<TouristAttraction> getAllAttractions() {
        return attractions;
    }

    public Optional<TouristAttraction> getAttractionByName(String name) {
        return attractions.stream()
                .filter(attraction -> attraction.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    public boolean updateAttraction(String name, TouristAttraction updatedAttraction) {
        Optional<TouristAttraction> attractionOpt = getAttractionByName(name);
        if (attractionOpt.isPresent()) {
            TouristAttraction attraction = attractionOpt.get();
            attraction.setLocation(updatedAttraction.getLocation());
            attraction.setRating(updatedAttraction.getRating());
            attraction.setTags(updatedAttraction.getTags());
            return true;
        }
        return false;
    }

    public boolean deleteAttraction(String name) {
        return attractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(name));
    }

    public List<String> getCities() {
        return attractions.stream()
                .map(TouristAttraction::getLocation)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getTags() {
        return attractions.stream()
                .flatMap(attraction -> attraction.getTags().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
