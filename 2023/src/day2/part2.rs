use std::cmp::Ordering;

use crate::io::day_input;

#[derive(Debug)]
enum Color {
    Green,
    Red,
    Blue,
}

#[derive(Debug)]
struct Move {
    color: Color,
    amount: u32,
}

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

fn max<T: PartialOrd>(a: T, b: T) -> T {
    let cmp = a.partial_cmp(&b);
    match cmp {
        Some(Ordering::Greater) => a,
        _ => b,
    }
}

fn game_power(line: &str) -> u32 {
    let game_sets = get_game_sets(line);
    let mut max_cubes = (0, 0, 0); // green, red, blue
    for set in game_sets.1 {
        let mut cubes_in_set = (0, 0, 0);
        for mov in set {
            match mov.color {
                Color::Green => cubes_in_set.0 += mov.amount,
                Color::Red => cubes_in_set.1 += mov.amount,
                Color::Blue => cubes_in_set.2 += mov.amount,
            }
        }
        max_cubes = (
            max(max_cubes.0, cubes_in_set.0),
            max(max_cubes.1, cubes_in_set.1),
            max(max_cubes.2, cubes_in_set.2),
        );
    }
    return max_cubes.0 * max_cubes.1 * max_cubes.2;
}

pub fn run() {
    let input = day_input(2);
    let valid_games: u32 = input.lines().map(|line| game_power(line)).sum();
    println!("Valid games: {}", valid_games);
}
