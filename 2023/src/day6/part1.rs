use crate::common::io::day_input;

pub fn run() {
    let input = day_input(6);
    let numbers = input
        .lines()
        .map(|l| {
            l.split_whitespace()
                .skip(1)
                .map_while(|s| s.parse::<usize>().ok())
                .collect::<Vec<_>>()
        })
        .collect::<Vec<_>>();
    let times = numbers.first().unwrap().iter();
    let distances = numbers.last().unwrap().iter();

    let mut result = 1;
    for (&time, &distance) in times.zip(distances) {
        let mut winning_conditions = 0;
        for time_held in 0..time {
            let distance_traveled = time_held * (time - time_held);
            if distance_traveled > distance {
                winning_conditions += 1;
            }
        }
        result *= winning_conditions;
    }

    println!("{result}");
}
