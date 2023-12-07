use crate::common::io::day_input;

pub fn run() {
    let input = day_input(6);
    let numbers = input
        .lines()
        .map(|l| {
            l.split(":")
                .nth(1)
                .unwrap()
                .replace(" ", "")
                .trim()
                .parse::<usize>()
                .unwrap()
        })
        .collect::<Vec<_>>();
    let time = *numbers.first().unwrap();
    let distance = *numbers.last().unwrap();

    let mut winning_conditions = 0;
    for time_held in 0..time {
        let distance_traveled = time_held * (time - time_held);
        if distance_traveled > distance {
            winning_conditions += 1;
        }
    }

    println!("{winning_conditions}");
}
