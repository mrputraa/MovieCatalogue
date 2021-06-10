package com.example.nontonkuy.utils

import com.example.nontonkuy.data.MovieEntity
import com.example.nontonkuy.data.SeriesEntity
import com.example.nontonkuy.data.source.remote.Movie
import com.example.nontonkuy.data.source.remote.Series

object DataDummy {
    fun generateDummyMovies(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(1,
            "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.",
            "Se7en",
            "https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg",
            8.6,
            "1995")
        )
        movies.add(
            Movie(2,
            "After his family was brutally murdered and his sister turned into a demon, Tanjiro Kamado's journey as a demon slayer began. Tanjiro and his comrades embark on a new mission aboard the Mugen Train, on track to despair.",
            "Demon Slayer: Mugen Train",
            "https://m.media-amazon.com/images/M/MV5BODI2NjdlYWItMTE1ZC00YzI2LTlhZGQtNzE3NzA4MWM0ODYzXkEyXkFqcGdeQXVyNjU1OTg4OTM@._V1_UX182_CR0,0,182,268_AL_.jpg",
            8.4,
            "2020")
        )
        movies.add(
            Movie(3,
            "Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.",
            "Saving Private Ryan",
            "https://m.media-amazon.com/images/M/MV5BZjhkMDM4MWItZTVjOC00ZDRhLThmYTAtM2I5NzBmNmNlMzI1XkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_UY268_CR0,0,182,268_AL_.jpg",
            8.6,
            "1998")
        )
        movies.add(
            Movie(4,
            "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
            "Lord of The Rings: The Return of The King",
            "https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX182_CR0,0,182,268_AL_.jpg",
            8.9,
            "2003")
        )
        movies.add(
            Movie(5,
            "Laurie Strode confronts her long-time foe Michael Myers, the masked figure who has haunted her since she narrowly escaped his killing spree on Halloween night four decades ago.",
            "Halloween",
            "https://m.media-amazon.com/images/M/MV5BMmMzNjJhYjUtNzFkZi00MWQ4LWJiMDEtYWM0NTAzNGZjMTI3XkEyXkFqcGdeQXVyOTE2OTMwNDk@._V1_UX182_CR0,0,182,268_AL_.jpg",
            6.5,
            "2018")
        )
        movies.add(
            Movie(6,
            "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
            "Zack Snyder's Justice League",
            "https://m.media-amazon.com/images/M/MV5BYjI3NDg0ZTEtMDEwYS00YWMyLThjYjktMTNlM2NmYjc1OGRiXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_UX182_CR0,0,182,268_AL_.jpg",
            8.1,
            "2021")
        )
        movies.add(
            Movie(7,
            "Two friends are searching for their long lost companion. They revisit their college days and recall the memories of their friend who inspired them to think differently, even as the rest of the world called them \"idiots\".",
            "3 Idiots",
            "\"https://m.media-amazon.com/images/M/MV5BNTkyOGVjMGEtNmQzZi00NzFlLTlhOWQtODYyMDc2ZGJmYzFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UY268_CR2,0,182,268_AL_.jpg",
            8.4,
            "2009")
        )
        movies.add(
            Movie(8,
            "In Shanghai, China in the 1940s, a wannabe gangster aspires to join the notorious \"Axe Gang\" while residents of a housing complex exhibit extraordinary powers in defending their turf.",
            "Kung Fu Hustle",
            "https://m.media-amazon.com/images/M/MV5BMjZiOTNlMzYtZWYwZS00YWJjLTk5NDgtODkwNjRhMDI0MjhjXkEyXkFqcGdeQXVyMjgyNjk3MzE@._V1_UY268_CR4,0,182,268_AL_.jpg",
            7.7,
            "2004")
        )
        movies.add(
            Movie(9,
            "On a journey to find the cure for a Tatarigami's curse, Ashitaka finds himself in the middle of a war between the forest gods and Tatara, a mining colony. In this quest he also meets San, the Mononoke Hime.",
            "Princess Mononoke",
            "https://m.media-amazon.com/images/M/MV5BNGIzY2IzODQtNThmMi00ZDE4LWI5YzAtNzNlZTM1ZjYyYjUyXkEyXkFqcGdeQXVyODEzNjM5OTQ@._V1_UX182_CR0,0,182,268_AL_.jpg",
            8.4,
            "1997")
        )
        movies.add(
            Movie(10,
            "Godzilla Vs. Kong",
            "The epic next chapter in the cinematic Monsterverse pits two of the greatest icons in motion picture history against one another - the fearsome Godzilla and the mighty Kong - with humanity caught in the balance.",
            "https://m.media-amazon.com/images/M/MV5BZmYzMzU4NjctNDI0Mi00MGExLWI3ZDQtYzQzYThmYzc2ZmNjXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_UX182_CR0,0,182,268_AL_.jpg",
            6.4,
            "2021")
        )
        return movies
    }

    fun generateDummySeries(): ArrayList<Series> {
        val series = ArrayList<Series>()

        series.add(
            Series(11,
            "In a war-torn world of elemental magic, a young boy reawakens to undertake a dangerous mystic quest to fulfill his destiny as the Avatar, and bring peace to the world.",
            " Avatar: The Last Airbender",
            "https://m.media-amazon.com/images/M/MV5BODc5YTBhMTItMjhkNi00ZTIxLWI0YjAtNTZmOTY0YjRlZGQ0XkEyXkFqcGdeQXVyODUwNjEzMzg@._V1_UX182_CR0,0,182,268_AL_.jpg",
                9.2,
                62,
                "2005")
        )
        series.add(
            Series(12,
                "While strange rumors about their ill King grip a kingdom, the crown prince becomes their only hope against a mysterious plague overtaking the land.",
            "Kingdom",
                "https://m.media-amazon.com/images/M/MV5BNTBlZmE4YzItNTY5Mi00NmIxLTlhZTAtOWIxNjFlNTMzNmI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
                8.4,
                13,
                "2019")
        )
        series.add(
            Series(13,
                "A group of vigilantes set out to take down corrupt superheroes who abuse their superpowers.",
            "The Boys",
                "https://m.media-amazon.com/images/M/MV5BNGEyOGJiNWEtMTgwMi00ODU4LTlkMjItZWI4NjFmMzgxZGY2XkEyXkFqcGdeQXVyNjcyNjcyMzQ@._V1_UX182_CR0,0,182,268_AL_.jpg",
                8.7,
                24,
                "2019")
        )
        series.add(
            Series(14,
                "In a dystopian America dominated by Nazi Germany and Imperial Japan, a young woman discovers a mysterious film that may hold the key to toppling the totalitarian regimes.",
            "The Man in the High Castle",
                "https://m.media-amazon.com/images/M/MV5BZWEwNzQ4NzUtMWRmYS00NDdiLTg5NDItODA5M2M4YTM0ZTE2XkEyXkFqcGdeQXVyMTAzNjU2NjM1._V1_UX182_CR0,0,182,268_AL_.jpg",
                8.0,
                40,
            "2015")
        )
        series.add(
            Series(15,
                "An animated series that follows the exploits of a super scientist and his not-so-bright grandson.",
            "Rick and Morty",
                "https://m.media-amazon.com/images/M/MV5BZjRjOTFkOTktZWUzMi00YzMyLThkMmYtMjEwNmQyNzliYTNmXkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UX182_CR0,0,182,268_AL_.jpg",
                9.2,
                42,
                "2013")
        )
        series.add(
            Series(16,
                "A collection of animated short stories that span various genres including science fiction, fantasy, horror and comedy.",
            "Love, Death & Robots",
                "https://m.media-amazon.com/images/M/MV5BYjEwOWQ0MjktMjZjNy00Mzc1LWE5NTItMDQ1Yjc0Zjk0NTBlXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_UX182_CR0,0,182,268_AL_.jpg",
                8.5,
                26,
                "2019")
        )
        series.add(
            Series(17,
                "A family of former child heroes, now grown apart, must reunite to continue to protect the world.",
            "The Umbrella Academy",
                "https://m.media-amazon.com/images/M/MV5BNzA5MjkwYzMtNGY2MS00YWRjLThkNTktOTNmMzdlZjE3Y2IxXkEyXkFqcGdeQXVyMjkwMzMxODg@._V1_UY268_CR0,0,182,268_AL_.jpg",
                8.0,
                30,
                "2019")
        )
        series.add(
            Series(18,
                "Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
            "Friends",
                "https://m.media-amazon.com/images/M/MV5BNDVkYjU0MzctMWRmZi00NTkxLTgwZWEtOWVhYjZlYjllYmU4XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UY268_CR0,0,182,268_AL_.jpg",
                8.9,
                235,
            "1994")
        )
        series.add(
            Series(19,
                "Two brothers search for a Philosopher's Stone after an attempt to revive their deceased mother goes awry and leaves them in damaged physical forms.",
            "Fullmetal Alchemist Brotherhood",
                "https://m.media-amazon.com/images/M/MV5BZmEzN2YzOTItMDI5MS00MGU4LWI1NWQtOTg5ZThhNGQwYTEzXkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UY268_CR4,0,182,268_AL_.jpg",
                9.1,
                69,
                "2009")
        )
        series.add(
            Series(20,
                "An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history - stealing 2.4 billion euros from the Royal Mint of Spain.",
            "Money Heist",
                "https://m.media-amazon.com/images/M/MV5BZDcxOGI0MDYtNTc5NS00NDUzLWFkOTItNDIxZjI0OTllNTljXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
                8.3,
                41,
                "2017")
        )
        return series
    }

}