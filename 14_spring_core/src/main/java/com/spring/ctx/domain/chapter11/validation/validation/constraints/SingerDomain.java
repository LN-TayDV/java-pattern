package com.spring.ctx.domain.chapter11.validation.validation.constraints;


import com.spring.ctx.domain.chapter11.validation.validation.constraints.custom.CheckCountrySinger;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@CheckCountrySinger
public class SingerDomain {

    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    private String lastName;

    @NotNull
    private Genre genre;

    private Gender gender;

    public boolean isCountrySinger() {
        return genre == Genre.COUNTRY;
    }

    public enum Genre {
        POP("P"),
        JAZZ("J"),
        BLUES("B"),
        COUNTRY("C");
        private final String code;

        Genre(String code) {
            this.code = code;
        }

        public String toString() {
            return this.code;
        }
    }

    public enum Gender {
        MALE("M"),
        FEMALE("F"),
        UNSPECIFIED("U");

        private final String code;

        Gender(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return this.code;
        }
    }

}
