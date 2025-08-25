package com.propertyservice.repository;

import com.propertyservice.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
//    @Query("""
//		    SELECT DISTINCT p
//		    FROM Property p
//		    JOIN p.rooms r
//		    JOIN RoomAvailability ra ON ra.room = r
//		    WHERE (
//		        LOWER(p.city.name) LIKE LOWER(CONCAT('%', :name, '%')) OR
//		        LOWER(p.area.name) LIKE LOWER(CONCAT('%', :name, '%')) OR
//		        LOWER(p.state.name) LIKE LOWER(CONCAT('%', :name, '%'))
//		    )
//		    AND ra.availableDate = :date
//		""")
//    List<Property> searchProperty(@Param("name") String name, @Param("date") LocalDate date);
}
