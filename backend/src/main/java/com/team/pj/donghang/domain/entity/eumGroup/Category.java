package com.team.pj.donghang.domain.entity.eumGroup;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    SHOP("ShoppingDetail",1),
    RESTAURANT("RestaurantDetail",2),
    FESTIVAL("FestivalDetail",3),
    CULTURE("CultureDetail",4),
    LEISURE("LeisureDetail",5),
    TOUR("TouristSpotDetail",6)
    ;

    private final String label;
    private final int number;

    Category(String label,int number){
        this.label =label;
        this.number = number;
    }

    public String label(){
        return label;
    }

    public int number(){
        return number;
    }

    private static final Map<String ,Category> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(Category::label, Function.identity()));
    private static final Map<Integer ,Category> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(Category::number, Function.identity()));

    public static Category valueOfLabel(String label){
        return BY_LABEL.get(label);
    }
    public static Category valueOfNumber(int number){
        return BY_NUMBER.get(number);
    }
}
