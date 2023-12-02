use crate::io::day_input;

enum Color {
    Green,
    Red,
    Blue,
}

struct Move {
    color: Color,
    amount: u32,
}

const RED_CUBES: u32 = 12;
const GREEEN_CUBES: u32 = 13;
const BLUE_CUBES: u32 = 14;

fn get_game_sets(line: &str) -> (u32, Vec<Vec<Move>>) {
    let game_split = line.split(":").collect::<Vec<_>>();
    let sets_str = game_split[1];
    let game_sets = sets_str
        .trim()
        .split(";")
        .into_iter()
        .map(|set| {
            set.split(",")
                .into_iter()
                .map(|mov| {
                    let amount_color: Vec<&str> = mov.trim().split(" ").collect();
                    //dbg!(mov, &amount_color);
                    let color = match amount_color[1] {
                        "green" => Color::Green,
                        "red" => Color::Red,
                        "blue" => Color::Blue,
                        _ => todo!("Color {} not implemented", amount_color[1]),
                    };
                    Move {
                        color,
                        amount: amount_color[0]
                            .parse()
                            .expect(&format!("Could not parse {:?}", amount_color)),
                    }
                })
                .collect::<Vec<_>>()
        })
        .collect::<Vec<_>>();
    let game_id = game_split[0].trim().split(" ").collect::<Vec<_>>()[1]
        .trim()
        .parse::<u32>()
        .expect("Could not retrieve game id");
    return (game_id, game_sets);
}

fn is_valid_game(line: &str) -> (bool, u32) {
    let game_sets = get_game_sets(line);
    let mut revealed_cubes = (0, 0, 0); // green, red, blue
    for set in game_sets.1 {
        for mov in set {
            match mov.color {
                Color::Green => revealed_cubes.0 += mov.amount,
                Color::Red => revealed_cubes.1 += mov.amount,
                Color::Blue => revealed_cubes.2 += mov.amount,
            }
        }
        if revealed_cubes.0 > GREEEN_CUBES
            || revealed_cubes.1 > RED_CUBES
            || revealed_cubes.2 > BLUE_CUBES
        {
            return (false, 0);
        }
        revealed_cubes = (0, 0, 0);
    }
    return (true, game_sets.0);
}

const INPUT: &str = ("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");

pub fn run() {
    let input = day_input(2);
    let valid_games: u32 = input
        .lines()
        .map(|line| is_valid_game(line))
        .filter(|game| game.0)
        .map(|game| game.1)
        .sum();
    println!("Valid games: {}", valid_games);
}
